package DatagramSocket;

class Collaborator implements java.io.Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int a;
    public String b;
 
    // Default constructor
    public Collaborator(int a, String b)
    {
        this.a = a;
        this.b = b;
    }
 
}
