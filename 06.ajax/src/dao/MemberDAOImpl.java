package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Member;

public class MemberDAOImpl extends BaseDAO implements MemberDAO {

	public static final String MEMBER_ID_CHECK = "select count(*) as cnt from member where id = ?";

	public int idcheck(String id) {

		int cnt=0;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(MEMBER_ID_CHECK);
			preparedStatement.setString(1, id);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				

				cnt = resultSet.getInt("cnt");
				

			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {

			closeDBObjects(resultSet, preparedStatement, connection);
		}

		return cnt;

	}

}
