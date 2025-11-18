package fr.univpoitiers.backrooms;

/**
 * Hello world!
 *
 */
public class Main
{
    public static void main( String[] args )
    {
        Locations r1 = new Locations("- Level 0 -\n\n" + "You wake up in an endless maze of yellow-tinted rooms, dimly lit by flickering fluorescent lights that hum with an unbearable buzz. The wallpaper is old and peeling, the carpet is damp beneath your feet, and the air smells faintly of mold and electricity. Every room looks the same, yet none are truly identical. You walk straight, turn around, retrace your steps - only to find yo!urself somewhere new. The rules of space don't seem to apply here.\n" );
        Locations r2 = new Locations("- Level 1 - \n\n" + "The maddening hum of the yellow wallpaper has faded. You are somewhere else.The air is cold and smells of damp concrete and... something strangely sweet. Almonds. You are in a vast warehouse. High above, industrial fluorescent lights cast a harsh, reliable glow. Unlike the flicker of before, the power here is constant. But the floor is wet. Pools of milky water steam gently, releasing a thick, low-hanging fog that clings to the ground. It obscures everything. Abandoned forklifts sit like sleeping beasts in the mist. This is Level 1. They call it the 'Habitable Zone'. The reliable electricity means you are not the first. Others are here, surviving in groups, hidden by the same fog that hides you. You can hear distant echoes, but the mist muffles the sound. Stairwells and dark corridors branch off into the haze, leading to different zones.");
        Locations r3 = new Locations("- Level 2 - \n\n" + "C'est le level 2");
        Locations r4 = new Locations("- Level 3 - \n\n" + "C'est le level 3");
        Locations r5 = new Locations("- Level 4 - \n\n" + "C'est le level 4");

        Characters C1 = new Characters(100,"CACA", 20, "je mange les pieds");
    }
}
