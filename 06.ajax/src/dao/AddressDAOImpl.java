package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Address;

public class AddressDAOImpl extends BaseDAO implements AddressDAO {

	public static final String ADDRESS_INSERT_SQL = "insert into address values(?,?,?,?,?)";

	public boolean insert(Address address) {
		boolean result = false;
		Connection connection =null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(ADDRESS_INSERT_SQL);
			
			
			preparedStatement.setInt(1,address.getPostcode());
			preparedStatement.setString(2,address.getRoadAddress());
			preparedStatement.setString(3,address.getJibunAddress());
			preparedStatement.setString(4,address.getDetailAddress());
			preparedStatement.setString(5,address.getExtraAddress());
			
			
			
			
			int rowCount = preparedStatement.executeUpdate();
			
			if(rowCount>0) {
				result = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			
			closeDBObjects(null,preparedStatement,connection);
			
		}
		return result;
		
	}
		
}
