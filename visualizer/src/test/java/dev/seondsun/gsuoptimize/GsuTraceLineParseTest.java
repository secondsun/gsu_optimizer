package dev.seondsun.gsuoptimize;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * This file tests parsing a line from a GSU trace on Mesen2
 */
public class GsuTraceLineParseTest {
    
    public static String TRACE_LINE = "70004E AND R4                                 SRC:00 DST:00 R0:0794 R1:0566 R2:0000 R3:0574 R4:0AA0 R5:0000 R6:0088 R7:0A00 R8:A000 R9:0000 R10:0594 R11:0501 R12:0000 R13:0178 R14:0000 R15:004E SFR:i--pji---pRVscz- V:155 H:288 Cycle:2356767";


    @Test
    public void parseLineTest() {
        GsuLine line = /*GsuLine.*/parse(TRACE_LINE);
        assertEquals(0x70004E, line.getAddress());
        assertEquals("AND", line.getInstruction());
    }


    @Test
    public void createBlocksTest() {

        var trace = """
            700172 TO R3                                  SRC:00 DST:00 R0:059E R1:059E R2:0794 R3:0088 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A6 R11:044D R12:0000 R13:03F3 R14:0000 R15:0172 SFR:i--pji---pRVsCz- V:157 H:20  Cycle:2358427
            700173 IWT R12,#$0003                         SRC:02 DST:02 R0:059E R1:059E R2:0794 R3:0088 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A6 R11:044D R12:0000 R13:03F3 R14:0000 R15:0173 SFR:i--Pji---pRVsCz- V:157 H:21  Cycle:2358432
            700174 LSR                                    SRC:00 DST:00 R0:059E R1:059E R2:0794 R3:0794 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A6 R11:044D R12:0000 R13:03F3 R14:0000 R15:0174 SFR:i--pji---pRVsCz- V:157 H:22  Cycle:2358437
            700177 TO R13                                 SRC:00 DST:00 R0:059E R1:059E R2:0794 R3:0794 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A6 R11:044D R12:0003 R13:03F3 R14:0000 R15:0177 SFR:i--pji---pRVsCz- V:157 H:26  Cycle:2358452
            700178 LDW (R1)                               SRC:0F DST:0F R0:059E R1:059E R2:0794 R3:0794 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A6 R11:044D R12:0003 R13:03F3 R14:0000 R15:0178 SFR:i--Pji---pRVsCz- V:157 H:27  Cycle:2358457
            700179 STW (R2)                               SRC:00 DST:00 R0:059E R1:059E R2:0794 R3:0794 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A6 R11:044D R12:0003 R13:0178 R14:0000 R15:0179 SFR:i--pji---pRVsCz- V:157 H:28  Cycle:2358462
            70017A WITH R2                                SRC:00 DST:00 R0:5000 R1:059E R2:0794 R3:0794 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A6 R11:044D R12:0003 R13:0178 R14:0000 R15:017A SFR:i--pji---pRVsCz- V:157 H:30  Cycle:2358467
            70017B ALT2                                   SRC:00 DST:00 R0:5000 R1:059E R2:0794 R3:0794 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A6 R11:044D R12:0003 R13:0178 R14:0000 R15:017B SFR:i--pji---pRVsCz- V:157 H:32  Cycle:2358482
            70017C ADD R2                                 SRC:02 DST:02 R0:5000 R1:059E R2:0794 R3:0794 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A6 R11:044D R12:0003 R13:0178 R14:0000 R15:017C SFR:i--Pji---pRVsCz- V:157 H:35  Cycle:2358487
            70017D WITH R1                                SRC:02 DST:02 R0:5000 R1:059E R2:0794 R3:0794 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A6 R11:044D R12:0003 R13:0178 R14:0000 R15:017D SFR:i--pji2--pRVsCz- V:157 H:36  Cycle:2358492
            70017E ALT2                                   SRC:00 DST:00 R0:5000 R1:059E R2:0796 R3:0794 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A6 R11:044D R12:0003 R13:0178 R14:0000 R15:017E SFR:i--pji---pRVscz- V:157 H:37  Cycle:2358497
            70017F ADD R2                                 SRC:01 DST:01 R0:5000 R1:059E R2:0796 R3:0794 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A6 R11:044D R12:0003 R13:0178 R14:0000 R15:017F SFR:i--Pji---pRVscz- V:157 H:38  Cycle:2358502
            700180 LOOP                                   SRC:01 DST:01 R0:5000 R1:059E R2:0796 R3:0794 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A6 R11:044D R12:0003 R13:0178 R14:0000 R15:0180 SFR:i--pji2--pRVscz- V:157 H:40  Cycle:2358507
            700181 NOP                                    SRC:00 DST:00 R0:5000 R1:05A0 R2:0796 R3:0794 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A6 R11:044D R12:0003 R13:0178 R14:0000 R15:0181 SFR:i--pji---pRVscz- V:157 H:41  Cycle:2358512
            700178 LDW (R1)                               SRC:00 DST:00 R0:5000 R1:05A0 R2:0796 R3:0794 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A6 R11:044D R12:0002 R13:0178 R14:0000 R15:0178 SFR:i--pji---pRVscz- V:157 H:42  Cycle:2358517
            700179 STW (R2)                               SRC:00 DST:00 R0:5000 R1:05A0 R2:0796 R3:0794 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A6 R11:044D R12:0002 R13:0178 R14:0000 R15:0179 SFR:i--pji---pRVscz- V:157 H:43  Cycle:2358522
            70017A WITH R2                                SRC:00 DST:00 R0:9180 R1:05A0 R2:0796 R3:0794 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A6 R11:044D R12:0002 R13:0178 R14:0000 R15:017A SFR:i--pji---pRVscz- V:157 H:45  Cycle:2358527
            70017B ALT2                                   SRC:00 DST:00 R0:9180 R1:05A0 R2:0796 R3:0794 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A6 R11:044D R12:0002 R13:0178 R14:0000 R15:017B SFR:i--pji---pRVscz- V:157 H:47  Cycle:2358542
            70017C ADD R2                                 SRC:02 DST:02 R0:9180 R1:05A0 R2:0796 R3:0794 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A6 R11:044D R12:0002 R13:0178 R14:0000 R15:017C SFR:i--Pji---pRVscz- V:157 H:50  Cycle:2358547
            70017D WITH R1                                SRC:02 DST:02 R0:9180 R1:05A0 R2:0796 R3:0794 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A6 R11:044D R12:0002 R13:0178 R14:0000 R15:017D SFR:i--pji2--pRVscz- V:157 H:51  Cycle:2358552
            70017E ALT2                                   SRC:00 DST:00 R0:9180 R1:05A0 R2:0798 R3:0794 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A6 R11:044D R12:0002 R13:0178 R14:0000 R15:017E SFR:i--pji---pRVscz- V:157 H:52  Cycle:2358557
            70017F ADD R2                                 SRC:01 DST:01 R0:9180 R1:05A0 R2:0798 R3:0794 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A6 R11:044D R12:0002 R13:0178 R14:0000 R15:017F SFR:i--Pji---pRVscz- V:157 H:53  Cycle:2358562
            700180 LOOP                                   SRC:01 DST:01 R0:9180 R1:05A0 R2:0798 R3:0794 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A6 R11:044D R12:0002 R13:0178 R14:0000 R15:0180 SFR:i--pji2--pRVscz- V:157 H:55  Cycle:2358567
            700181 NOP                                    SRC:00 DST:00 R0:9180 R1:05A2 R2:0798 R3:0794 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A6 R11:044D R12:0002 R13:0178 R14:0000 R15:0181 SFR:i--pji---pRVscz- V:157 H:56  Cycle:2358572
            700178 LDW (R1)                               SRC:00 DST:00 R0:9180 R1:05A2 R2:0798 R3:0794 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A6 R11:044D R12:0001 R13:0178 R14:0000 R15:0178 SFR:i--pji---pRVscz- V:157 H:57  Cycle:2358577
            700179 STW (R2)                               SRC:00 DST:00 R0:9180 R1:05A2 R2:0798 R3:0794 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A6 R11:044D R12:0001 R13:0178 R14:0000 R15:0179 SFR:i--pji---pRVscz- V:157 H:58  Cycle:2358582
            70017A WITH R2                                SRC:00 DST:00 R0:C600 R1:05A2 R2:0798 R3:0794 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A6 R11:044D R12:0001 R13:0178 R14:0000 R15:017A SFR:i--pji---pRVscz- V:157 H:60  Cycle:2358587
            70017B ALT2                                   SRC:00 DST:00 R0:C600 R1:05A2 R2:0798 R3:0794 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A6 R11:044D R12:0001 R13:0178 R14:0000 R15:017B SFR:i--pji---pRVscz- V:157 H:62  Cycle:2358602
            70017C ADD R2                                 SRC:02 DST:02 R0:C600 R1:05A2 R2:0798 R3:0794 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A6 R11:044D R12:0001 R13:0178 R14:0000 R15:017C SFR:i--Pji---pRVscz- V:157 H:65  Cycle:2358607
            70017D WITH R1                                SRC:02 DST:02 R0:C600 R1:05A2 R2:0798 R3:0794 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A6 R11:044D R12:0001 R13:0178 R14:0000 R15:017D SFR:i--pji2--pRVscz- V:157 H:66  Cycle:2358612
            70017E ALT2                                   SRC:00 DST:00 R0:C600 R1:05A2 R2:079A R3:0794 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A6 R11:044D R12:0001 R13:0178 R14:0000 R15:017E SFR:i--pji---pRVscz- V:157 H:67  Cycle:2358617
            70017F ADD R2                                 SRC:01 DST:01 R0:C600 R1:05A2 R2:079A R3:0794 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A6 R11:044D R12:0001 R13:0178 R14:0000 R15:017F SFR:i--Pji---pRVscz- V:157 H:68  Cycle:2358622
            700180 LOOP                                   SRC:01 DST:01 R0:C600 R1:05A2 R2:079A R3:0794 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A6 R11:044D R12:0001 R13:0178 R14:0000 R15:0180 SFR:i--pji2--pRVscz- V:157 H:70  Cycle:2358627
            700181 NOP                                    SRC:00 DST:00 R0:C600 R1:05A4 R2:079A R3:0794 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A6 R11:044D R12:0001 R13:0178 R14:0000 R15:0181 SFR:i--pji---pRVscz- V:157 H:71  Cycle:2358632
            700182 DEC R10                                SRC:00 DST:00 R0:C600 R1:05A4 R2:079A R3:0794 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A6 R11:044D R12:0000 R13:0178 R14:0000 R15:0182 SFR:i--pji---pRVscZ- V:157 H:72  Cycle:2358637
            700183 DEC R10                                SRC:00 DST:00 R0:C600 R1:05A4 R2:079A R3:0794 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A6 R11:044D R12:0000 R13:0178 R14:0000 R15:0183 SFR:i--pji---pRVscZ- V:157 H:73  Cycle:2358642
            700184 TO R15                                 SRC:00 DST:00 R0:C600 R1:05A4 R2:079A R3:0794 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A5 R11:044D R12:0000 R13:0178 R14:0000 R15:0184 SFR:i--pji---pRVscz- V:157 H:75  Cycle:2358647
            700185 LDW (R10)                              SRC:00 DST:00 R0:C600 R1:05A4 R2:079A R3:0794 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A4 R11:044D R12:0000 R13:0178 R14:0000 R15:0185 SFR:i--pji---pRVscz- V:157 H:76  Cycle:2358652
            700186 NOP                                    SRC:00 DST:0F R0:C600 R1:05A4 R2:079A R3:0794 R4:0000 R5:DA40 R6:4000 R7:2200 R8:0000 R9:C600 R10:05A4 R11:044D R12:0000 R13:0178 R14:0000 R15:0186 SFR:i--pji---pRVscz- V:157 H:77  Cycle:2358657            
                """;

        Graph graph = visualize(trace);

        assertEquals (3, graph.getBlocks().size());
        assertEquals (3, graph.getBlocks().size());
        assertEquals (3, graph.getBlocks().size());
        
        fail("""
                TODO: The above needs to make 3 blocks. there is the loop preamble, the loop body  and the after loop. 
                The loop body is from memory 0x178-0x181. 0x182 is the first memory of the after loop
                The preamble is from 172 - 177.
                """
        );
    }


    private Graph visualize(String trace) {
        return null;
    }


    private GsuLine parse(String traceLine) {
        String[] parts = traceLine.trim().split("\s+");
        GsuLine line = ( GsuLine.Builder()
                        .setAddress(parts[0]))
                        .setInstruction(parts[1])
                        .setArguments(parts[2])
                        .setCycle(parts[parts.length-1].split(":")[1])
                        .build();

        return line;
    }

}
