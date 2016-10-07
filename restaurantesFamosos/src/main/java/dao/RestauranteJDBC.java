package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import conexao.Conexao;
import model.CEP;
import model.Restaurante;

public class RestauranteJDBC implements RestauranteDAO {

	private Conexao conexao;

	public RestauranteJDBC(Conexao conexao) {
		this.conexao = conexao;
	}

	public void inserir(Restaurante objeto) {
		String insert = "insert into Restaurante (Nome,Telefone,Tipo_de_estabelecimento,Rua,Numero,Tema,idCEP) values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conexao.get().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, objeto.getNome());
			ps.setString(2, objeto.getTelefone());
			ps.setString(3, objeto.getTipo());
			ps.setString(4, objeto.getRua());
			ps.setString(5, objeto.getNumero());
			ps.setString(6, objeto.getTema());
			ps.setLong(7, objeto.getCep().getCodigo());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			objeto.setCodigo(rs.getLong(1));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
	}

	public void alterar(Restaurante objeto) {
		String update = "update Restaurante set Nome=?, set Telefone=?, set Tipo_de_estabelecimento=?, set Rua =?, set Numero=?, set Tema=?"
				+ "where idRestaurante = ?";
		try {
			PreparedStatement ps = conexao.get().prepareStatement(update);
			ps.setString(1, objeto.getNome());
			ps.setString(2, objeto.getTelefone());
			ps.setString(3, objeto.getTipo());
			ps.setString(4, objeto.getRua());
			ps.setString(5, objeto.getNumero());
			ps.setString(6, objeto.getTema());
			ps.setLong(7, objeto.getCep().getCodigo());
			ps.setLong(8, objeto.getCodigo());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
	}

	public void excluir(Long codigo) {
		String del = "delete from Restaurante" + "where idRestaurante = ?";
		try {
			PreparedStatement ps = conexao.get().prepareStatement(del);
			ps.setLong(1, codigo);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}

	}

	public Collection<Restaurante> todos() {
		String sql = "select *from Restaurante";
		List<Restaurante> restaurantes = new ArrayList<>();
		try {
			PreparedStatement ps = conexao.get().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			restaurantes = getLista(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
		return restaurantes;
	}

	public Restaurante get(Long codigo) {
		String sql = "select *from Restaurante where idRestaurante =?";
		Restaurante restaurante = null;
		try {
			PreparedStatement ps = conexao.get().prepareStatement(sql);
			ps.setLong(1, codigo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				restaurante = getRestaurante(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
		return restaurante;
	}

	private List<Restaurante> getLista(ResultSet rs) throws SQLException {
		List<Restaurante> restaurantes = new ArrayList<>();
		while (rs.next()) {
			restaurantes.add(getRestaurante(rs));
		}
		return restaurantes;
	}

	private Restaurante getRestaurante(ResultSet rs) throws SQLException {
		Restaurante restaurante = new Restaurante(rs.getLong("idRestaurante"), rs.getString("Nome"),
				rs.getString("Telefone"), rs.getString("Tipo_de_estabelecimento"), rs.getString("Rua"),
				rs.getString("Numero"), rs.getString("Tema"), new CEP(rs.getLong("idCEP")));
		return restaurante;
	}

}