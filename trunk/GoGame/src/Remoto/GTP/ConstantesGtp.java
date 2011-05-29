package Remoto.GTP;

public class ConstantesGtp {

	//Datos Gtp
	public static final String VERSION_PROTOCOLO= "2";
	
	//Comandos requeridos
	public static final String PROTOCOL_VERSION= "protocol_version";
	public static final String QUIT= "quit";
	public static final String NAME= "name";
	public static final String VERSION= "version";
	public static final String BOARDSIZE= "boardsize";
	public static final String CLEAR_BOARD= "clear_board";
	public static final String PLAY= "play";
	public static final String KOMI= "komi";
	public static final String GENMOVE= "genmove";
	public static final String KNOWN_COMMAND= "known_command";
	public static final String LIST_COMMANDS= "list_commands";
	
	//Mensajes
	public static final String FIN_MSJ= "\n";
	public static final String INICIO_MSJ_RTA= "=";
	public static final String INICIO_MSJ_RTA_ERROR= "?";
	public static final String FIN_MSJ_RTA= "\n\n";
	public static final String ESPACIO= " ";
	public static final String SHARP= "#";
	public static final String PASAR_TURNO= "pass";
}
