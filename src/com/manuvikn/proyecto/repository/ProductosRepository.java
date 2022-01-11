package com.manuvikn.proyecto.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.manuvikn.proyecto.database.DatabaseConnection;
import com.manuvikn.proyecto.dto.Productos;

public class ProductosRepository implements Repository<Productos> {
	
	private static ProductosRepository p;
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	private final String insertStatement = "INSERT INTO Productos ( nombre, descripcion, precio, fechaCompra ) VALUES( ?, ?, ?, ?)";
	private final String updateStatement = "UPDATE Productos SET nombre = ?, descripcion = ?, precio = ?, fechaCompra = ? WHERE idProductos = ?";
	private final String deleteStatement = "DELETE FROM Productos WHERE idProductos = ?";
	
	private ProductosRepository() {
		conn = DatabaseConnection.getInstance();
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static ProductosRepository getInstance() {
		if (p == null) {
			p = new ProductosRepository();
		}
		
		return p;
	}
	
	@Override
	public List<Productos> findAll() {
		// TODO Auto-generated method stub

		List<Productos> lp = new ArrayList();
		try {
			rs = stmt.executeQuery("SELECT * FROM Productos");

			while(rs.next()) {
				lp.add(new Productos(rs.getString("nombre"),
						rs.getString("descripcion"), rs.getDouble("precio"),
						rs.getDate("fechaCompra"), rs.getLong("idProductos")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return lp;
	}

	@Override
	public Productos getById(long id) {
		// TODO Auto-generated method stub
		
		Productos p = null;
		
		try {
			
			rs = stmt.executeQuery("SELECT * FROM Productos WHERE idProductos = " + id);
			if (rs.next()) {
				p = new Productos(rs.getString("nombre"), rs.getString("descripcion"),
						rs.getDouble("precio"), rs.getDate("fechaCompra"), rs.getLong("idProductos"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public long create(Productos t) {
		// TODO Auto-generated method stub
		
		long res = 0;
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(this.insertStatement);
			ps.setString(1, t.getNombre());
			ps.setString(2, t.getDescripcion());
			ps.setDouble(3, t.getPrecio());
			ps.setDate(4, t.getFechaCompra());
			res = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public long update(Productos t) {
		// TODO Auto-generated method stub
		
		long res = 0;
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(this.updateStatement);
			ps.setString(1, t.getNombre());
			ps.setString(2, t.getDescripcion());
			ps.setDouble(3, t.getPrecio());
			ps.setDate(4, t.getFechaCompra());
			ps.setLong(5, t.getIdProductos());
			res = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public long remove(long i) {
		// TODO Auto-generated method stub
		
		long res = 0;
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(this.deleteStatement);
			ps.setLong(1, i);
			res = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
	
	public void closeConnection() {
		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
