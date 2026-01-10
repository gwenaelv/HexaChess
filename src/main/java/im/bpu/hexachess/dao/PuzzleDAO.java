package im.bpu.hexachess.dao;

import im.bpu.hexachess.entity.Puzzle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

public class PuzzleDAO extends DAO<Puzzle> {
	@Override
	public Puzzle create(Puzzle puzzle) {
		String request =
			"INSERT INTO puzzles (puzzle_id, moves, solutions, rating, theme, created_at) "
			+ "VALUES(?, ?, ?, ?, ?, ?)";
		try (PreparedStatement pstmt = connect.prepareStatement(request)) {
			pstmt.setString(1, puzzle.getPuzzleId());
			pstmt.setString(2, puzzle.getMoves());
			pstmt.setString(3, puzzle.getSolutions());
			pstmt.setInt(4, puzzle.getRating());
			pstmt.setString(5, puzzle.getTheme());
			if (puzzle.getCreatedAt() != null)
				pstmt.setTimestamp(6, Timestamp.valueOf(puzzle.getCreatedAt()));
			else
				pstmt.setTimestamp(6, null);
			pstmt.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return puzzle;
	}
	@Override
	public Puzzle update(Puzzle puzzle) {
		String request = "UPDATE puzzles SET moves = ?, solutions = ?, rating = ?, theme = ? WHERE "
			+ "puzzle_id = ?";
		try (PreparedStatement pstmt = connect.prepareStatement(request)) {
			pstmt.setString(1, puzzle.getMoves());
			pstmt.setString(2, puzzle.getSolutions());
			pstmt.setInt(3, puzzle.getRating());
			pstmt.setString(4, puzzle.getTheme());
			pstmt.setString(5, puzzle.getPuzzleId());
			pstmt.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return puzzle;
	}
	@Override
	public void delete(Puzzle puzzle) {
		String request = "DELETE FROM puzzles WHERE puzzle_id = ?";
		try (PreparedStatement pstmt = connect.prepareStatement(request)) {
			pstmt.setString(1, puzzle.getPuzzleId());
			pstmt.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}
	private Puzzle resultSetToPuzzle(ResultSet rs) throws SQLException {
		Puzzle puzzle = new Puzzle(rs.getString("puzzle_id"), rs.getString("moves"),
			rs.getString("solutions"), rs.getInt("rating"), rs.getString("theme"),
			rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime()
												  : null);
		return puzzle;
	}
	public Puzzle read(String puzzleId) {
		Puzzle puzzle = null;
		String request = "SELECT * FROM puzzles WHERE puzzle_id = ?";
		try (PreparedStatement pstmt = connect.prepareStatement(request)) {
			pstmt.setString(1, puzzleId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					puzzle = resultSetToPuzzle(rs);
				}
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return puzzle;
	}
	public ArrayList<Puzzle> readAll() {
		ArrayList<Puzzle> puzzles = new ArrayList<>();
		String request = "SELECT * FROM puzzles";
		try (Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(request)) {
			while (rs.next()) {
				puzzles.add(resultSetToPuzzle(rs));
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return puzzles;
	}
}