package vfma;

public class VFMException extends Exception {

	public VFMException(String message) {
		super("VFM Error! Human interaction needed! " + message);
	}
	
}
