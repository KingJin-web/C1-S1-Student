package bean;

public class Teacher {

    private String tid;
    private int tno;
    private String tname;
    private String tpw;
    private int tlimi;
    public Teacher() {
    	
    }
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public int getTno() {
		return tno;
	}
	public void setTno(int tno) {
		this.tno = tno;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTpw() {
		return tpw;
	}
	public void setTpw(String tpw) {
		this.tpw = tpw;
	}
	public int getTlimi() {
		return tlimi;
	}
	public void setTlimi(int tlimi) {
		this.tlimi = tlimi;
	}
	@Override
	public String toString() {
		return "Teacher [tid=" + tid + ", tno=" + tno + ", tname=" + tname + ", tpw=" + tpw + ", tlimi=" + tlimi + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tid == null) ? 0 : tid.hashCode());
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
		Teacher other = (Teacher) obj;
		if (tid == null) {
			if (other.tid != null)
				return false;
		} else if (!tid.equals(other.tid))
			return false;
		return true;
	}

    
}
