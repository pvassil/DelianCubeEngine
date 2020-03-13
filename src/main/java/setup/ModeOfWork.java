package setup;

/**
 * A class that allows the definition of a working mode. By default set to EXPERIMENT.
 * 
 * The main construct is the enum WorkMode and the public static final attribute mode.
 * So, every time the server starts, it must be initialized appropriately.
 * Observe the semi-singleton structure, with the constructor defined as private.
 *  
 * @author pvassil
 *
 */
public class ModeOfWork {

	public enum WorkMode{DEV, EXPERIMENT, DEBUG_GLOBAL, DEBUG_QUERY, DEBUG_MODEL};
	
	public static final WorkMode mode = WorkMode.EXPERIMENT;
	
	private ModeOfWork() {
		;
	}

}
