package ru.skaliush.superlab.server.collection;

import ru.skaliush.superlab.common.models.*;
import ru.skaliush.superlab.common.models.dto.PersonDTO;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class PostgresCollectionManager implements CollectionManager {
    private final Connection connection;

    private Collection<Person> collection;

    public PostgresCollectionManager(Connection connection) {
        this.connection = connection;
        collection = loadCollection();
    }

    private Collection<Person> loadCollection() {
        try {
            Collection<Person> result = ConcurrentHashMap.newKeySet();
            ResultSet resultSet = connection.createStatement().executeQuery("select * from persons");
            while (resultSet.next()) {
                Person person = mapResultSetToPerson(resultSet);
                result.add(person);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Collection<Person> getCollection() {
        return collection;
    }

    public Person createPerson(PersonDTO personDTO) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into persons (name, height, eye_color, hair_color, nationality, location_x, location_y, location_z, location_name, owner_login) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) returning *");
            ps.setString(1, personDTO.getName());
            ps.setInt(2, personDTO.getHeight());
            ps.setInt(3, personDTO.getEyeColor().ordinal());
            ps.setInt(4, personDTO.getHairColor().ordinal());
            ps.setInt(5, personDTO.getNationality().ordinal());
            Location location = personDTO.getLocation();
            if (location != null) {
                ps.setFloat(6, location.getX());
                ps.setFloat(7, location.getY());
                ps.setFloat(8, location.getZ());
                ps.setString(9, location.getName());
            } else {
                ps.setNull(6, Types.REAL);
                ps.setNull(7, Types.REAL);
                ps.setNull(8, Types.REAL);
                ps.setNull(9, Types.VARCHAR);
            }
            ps.setString(10, personDTO.getOwnerLogin());
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            Person person = mapResultSetToPerson(resultSet);
            collection.add(person);
            return person;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Person getPersonById(Long id) {
        try {
            PreparedStatement ps1 = connection.prepareStatement("select * from persons where id = ?");
            ps1.setLong(1, id);
            ResultSet resultSet1 = ps1.executeQuery();
            if (resultSet1.next()) {
                return mapResultSetToPerson(resultSet1);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Person updatePersonById(Long id, PersonDTO personDTO) {
        try {
            PreparedStatement ps = connection.prepareStatement("update persons set name = ?, height = ?, eye_color = ?, hair_color = ?, nationality = ?, location_x = ?, location_y = ?, location_z = ?, location_name = ? where id = ? returning *");
            ps.setString(1, personDTO.getName());
            ps.setInt(2, personDTO.getHeight());
            ps.setInt(3, personDTO.getEyeColor().ordinal());
            ps.setInt(4, personDTO.getHairColor().ordinal());
            ps.setInt(5, personDTO.getNationality().ordinal());
            Location location = personDTO.getLocation();
            if (location != null) {
                ps.setFloat(6, location.getX());
                ps.setFloat(7, location.getY());
                ps.setFloat(8, location.getZ());
                ps.setString(9, location.getName());
            } else {
                ps.setNull(6, Types.REAL);
                ps.setNull(7, Types.REAL);
                ps.setNull(8, Types.REAL);
                ps.setNull(9, Types.VARCHAR);
            }
            ps.setLong(10, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                Person person = mapResultSetToPerson(resultSet);
                collection = loadCollection();
                return person;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean removePersonById(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from persons where id = ? returning *");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            collection = loadCollection();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int clearCollection() {
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select count(*) from persons");
            resultSet.next();
            int count = resultSet.getInt("count");
            connection.createStatement().execute("truncate persons");
            collection = ConcurrentHashMap.newKeySet();
            return count;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int clearCollection(User user) {
        try {
            PreparedStatement ps1 = connection.prepareStatement("select count(*) from persons where owner_login = ?");
            ps1.setString(1, user.getLogin());
            ResultSet resultSet = ps1.executeQuery();
            resultSet.next();
            int count = resultSet.getInt("count");
            PreparedStatement ps2 = connection.prepareStatement("delete from persons where owner_login = ?");
            ps2.setString(1, user.getLogin());
            ps2.execute();
            collection = loadCollection();
            return count;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addPersonIfMin(Person person) {
        //
    }

    public int removeGreaterThanHeight(int height) {
        return 0;
    }

    public int removeLowerThanHeight(int height) {
        return 0;
    }

    private Person mapResultSetToPerson(ResultSet resultSet) throws SQLException {
        PersonDTO personDTO = new PersonDTO();
        long id = resultSet.getLong("id");
        personDTO.setName(resultSet.getString("name"));
        personDTO.setHeight(resultSet.getInt("height"));
        personDTO.setEyeColor(Color.values()[resultSet.getInt("eye_color")]);
        personDTO.setHairColor(Color.values()[resultSet.getInt("hair_color")]);
        personDTO.setNationality(Country.values()[resultSet.getInt("nationality")]);
        personDTO.setLocation(new Location(
                resultSet.getFloat("location_x"),
                resultSet.getFloat("location_y"),
                resultSet.getFloat("location_z"),
                resultSet.getString("location_name")
        ));
        personDTO.setOwnerLogin(resultSet.getString("owner_login"));
        LocalDateTime creationDate = resultSet.getTimestamp("creation_date").toLocalDateTime();
        return new Person(id, personDTO, creationDate.atZone(ZoneId.systemDefault()));
    }
}
