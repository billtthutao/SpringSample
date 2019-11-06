package cadt.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Model implements Comparable<Model>{
	
	private String workItemNo;
	private String type;
	private String name;
	
	@Override
	public int compareTo(Model o) {
		// TODO Auto-generated method stub
		int match1 = this.workItemNo.compareTo(o.getWorkItemNo());
		int match2 = this.type.compareTo(o.getType());
		int match3 = this.name.compareTo(o.getName());
		
		if(match1 == 0){
			if(match2 == 0)
				return match3;
			else
				return match2;
		}else
			return match1;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Model other = (Model) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
}
