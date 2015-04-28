package de.briemla.clockradio.dabpi.result;

public class DABService {

	private final Integer number;
	private final String id;
	private final String name;

	public DABService(Integer number, String id, String name) {
		this.number = number;
		this.id = id;
		this.name = name;
	}

	public String serialize() {
		return String.valueOf(number);
	}

	public boolean checkId(String idToCheck) {
		return id.equals(idToCheck);
	}

	public boolean checkName(String nameToCheck) {
		return name.equals(nameToCheck);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		DABService other = (DABService) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (number == null) {
			if (other.number != null) {
				return false;
			}
		} else if (!number.equals(other.number)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return name;
	}

}
