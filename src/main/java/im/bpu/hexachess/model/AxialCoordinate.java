package im.bpu.hexachess.model;

import java.util.Objects;

public class AxialCoordinate {
	private static final int RADIUS = 5;
	public final int q;
	public final int r;
	public AxialCoordinate(int q, int r) {
		this.q = q;
		this.r = r;
	}
	public AxialCoordinate add(int dq, int dr) {
		return new AxialCoordinate(q + dq, r + dr);
	}
	public boolean isValid() {
		return Math.abs(q) <= RADIUS && Math.abs(r) <= RADIUS && Math.abs(q - r) <= RADIUS;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof AxialCoordinate coord))
			return false;
		return q == coord.q && r == coord.r;
	}
	@Override
	public int hashCode() {
		return Objects.hash(q, r);
	}
}