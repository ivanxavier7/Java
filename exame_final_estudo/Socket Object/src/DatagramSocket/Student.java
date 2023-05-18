package DatagramSocket;

class Student implements java.io.Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int studentAvg;
    public String studentName;
    
	public Student(int studentAvg, String studentName) {
		super();
		this.studentAvg = studentAvg;
		this.studentName = studentName;
	}
	
	public int getStudentAvg() {
		return studentAvg;
	}
	public void setStudentAvg(int studentAvg) {
		this.studentAvg = studentAvg;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
}
