package enums;

import java.util.Arrays;
import java.util.Optional;

public enum Command {

    //Arithmetic commands
    ADD("AA", 2),
    SUB("AB", 2),
    MUL("AC", 2),
    DIV("AD", 2),
    NOT("AE", 2),

    //Comparison commands
    CMP("C0",2),

    //Stack commands
    LD("80", 4),
    PT("81", 4),
    PUN("82", 4),
    PUS("83", 4),
    POP("84", 4),
    PN("85",4),

    //Control
    JP("F0", 4),
    JE("F1", 4),
    JL("F2", 4),
    JG("F3", 4),
    STOP("FFFE", 4),

    // I/O
    PRTS("50", 4),
    PRTN("51", 4),
    PB("11", 4),
    RHD("12", 4),
    READ("52", 4),
    HDD("20", 4),
    WHD("51", 4),
    STARTIO("00", 4),

    //Data loading
    DW("DB", 4),
    DN("DC", 4),
    DD("DD", 4),

    //Interupt commands
    //MOV("CA",4),
    SVW("CB", 4),
    SVR("CC", 4),
    PUSH("CD", 4),
    TRNF("CF", 4 ),
    REGW("D0", 4),

    //Supervizor commands
    SVW0("B0",4),
    SVR0("B1",4),
    END("FFFF",4),
    MOV0("4",4), //MOV(register(1-10) , value (0-256)) pvz 42FF
    SPH("B3",4),
    PAP("B4",4);



    private final String hexCode;
    private final int commandLength;

    Command(String hexCode, int commandLength)
    {
        this.hexCode = hexCode;
        this.commandLength = commandLength;
    }

    /**
     * Gets a command by string or returns null
     * @param command
     * @return
     */
    public static Command getCommand(String command){
        if (command == null)
            return null;

        return findCommand(command).orElse(null);
    }

    private static Optional<Command> findCommand(String command){
        return Arrays.stream(values())
                .filter(com -> command.contains(com.toString())).findFirst();
    }

    public static Command getCommandByHex(String command){
        if (command == null)
            return null;

        return findCommandByHex(command).orElse(null);
    }

    private static Optional<Command> findCommandByHex(String command) {
        return Arrays.stream(values())
                .filter(c -> command.contains(c.hexCode)).findFirst();
    }

    public String getCode() {
        return hexCode;
    }

    /**
     * Strips the command string
     * Example: PUS XD or PUSXD -> XD
     * @param command
     */
    public String stripCommand(String command, boolean hex) {
        if (hex)
            command = command.replace(this.hexCode, "");
        else
            command = command.replace(this.toString(), "");

        command = command.trim();
        return command;
    }


}
