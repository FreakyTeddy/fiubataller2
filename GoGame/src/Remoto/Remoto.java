package Remoto;

import Remoto.GTP.GTP;

public class Remoto {

	protected GTP gtp;

	
	public Remoto() {
		gtp= new GTP(this);
	
	}
}
