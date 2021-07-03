package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.database.DBConnection;
import model.database.DBException;

public class ContatoDao {

	private PreparedStatement pst = null;
	private ResultSet res = null;
	private Connection con = null;
	private static ContatoDao contatoDao;

	private ContatoDao() {
	}

	public static ContatoDao getInstance() {
		if(contatoDao == null) {
			contatoDao = new ContatoDao();
		}
		return contatoDao;
	}

	public void inserirContato(Contato contato) {
		try {
			con = DBConnection.getConnection();
			pst = con.prepareStatement("insert into contatos (nome, fone, email) values (?, ?, ?)");
			
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());

			pst.executeUpdate();

		} catch (SQLException ex) {
			System.out.println(ex);
			throw new DBException(ex.getMessage());
		} finally {
			DBConnection.closeConnection(con, pst);
		}
	}

	public List<Contato> listarContatos() {
		try {
			con = DBConnection.getConnection();
			pst = con.prepareStatement("select * from contatos order by nome");

			return funcaoListar(pst);

		} catch (SQLException ex) {
			System.out.println(ex);
			throw new DBException(ex.getMessage());
		} finally {
			DBConnection.closeConnection(con, pst, res);
		}
	}
	
	public Contato buscarPorId(Integer id) {
		try {
			Contato contato = null;
			con = DBConnection.getConnection();
			pst = con.prepareStatement("select * from contatos where id = ?");
			
			pst.setInt(1, id);			
			res = pst.executeQuery();
			if(res.next()) {
				contato = instanciaContato(res);
			}
			return contato;

		} catch (SQLException ex) {
			System.out.println(ex);
			throw new DBException(ex.getMessage());
		} finally {
			DBConnection.closeConnection(con, pst, res);
		}
	}
	
	public void alterarContato(Contato contato) {
		try {
			con = DBConnection.getConnection();
			pst = con.prepareStatement("update contatos set nome = ?, fone = ?, email = ? where id = ? limit 1");
			
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setInt(4, contato.getId());

			pst.executeUpdate();

		} catch (SQLException ex) {
			System.out.println(ex);
			throw new DBException(ex.getMessage());
		} finally {
			DBConnection.closeConnection(con, pst);
		}
	}
	
	public void excluirContato(Integer id) {
		try {
			con = DBConnection.getConnection();
			pst = con.prepareStatement("delete from contatos where id = ? limit 1");

			pst.setInt(1, id);
			
			pst.executeUpdate();

		} catch (SQLException ex) {
			System.out.println(ex);
			throw new DBException(ex.getMessage());
		} finally {
			DBConnection.closeConnection(con, pst);
		}
	}

	private List<Contato> funcaoListar(PreparedStatement pst) throws SQLException {

		List<Contato> listaContato = new ArrayList<>();
		res = pst.executeQuery();
		
		while (res.next()) {
			listaContato.add(instanciaContato(res));
		}
		return listaContato;
	}

	private Contato instanciaContato(ResultSet res) throws SQLException {

		return new Contato(res.getInt("id"), res.getString("nome"),
				res.getString("fone"), res.getString("email"));
	}
}
