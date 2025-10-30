import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    boolean[][] screenFloor = new boolean[7][9];
    boolean[][] screenLeft = new boolean[7][9];
    boolean[][] screenJump = new boolean[7][9];
    boolean[][] screenNoEnemy = new boolean[7][9];
    boolean[][] map1 = new boolean[7][7];
    boolean[][] map2 = new boolean[7][7];

    @BeforeEach
    void setUp() {
        screenFloor = new boolean[][]{
                {false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false},
                {false, false, true, true, true, false, false, false, false},
                {false, false, true, true, true, false, false, false, false},
                {false, false, true, true, true, false, false, false, false},
                {false, false, true, true, true, false, false, false, false},
                {false, false, true, true, true, false, false, false, false}};

        screenLeft = new boolean[][]{
                {false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false},
                {true, true, true, false, false, false, false, false, false},
                {true, true, true, false, false, false, false, false, false},
                {true, true, true, false, false, false, false, false, false},
                {true, true, true, false, false, false, false, false, false},
                {true, true, true, false, false, false, false, false, false}};

        screenJump = new boolean[][]{
                {false, false, false, false, false, true, true, true, false},
                {false, false, false, false, false, true, true, true, false},
                {false, false, false, false, false, true, true, true, false},
                {false, false, false, false, false, true, true, true, false},
                {false, false, false, false, false, true, true, true, false},
                {false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false}};

        screenNoEnemy = new boolean[][]{
                {false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false}};

        map1 = new boolean[][]{
                {false, true, false, false, false, false, false},
                {false, false, false, false, false, false, false},
                {false, false, true, false, false, false, false},
                {true, false, false, false, false, false, true},
                {false, false, false, false, false, false, false},
                {false, false, false, false, false, true, false},
                {false, true, false, false, false, false, true}};

        map2 = new boolean[][]{
                {false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false}};

    }

    @Test
    void getDamage() {
        assertEquals(Main.getDamage(650, 75, 3), 65);
        assertEquals(Main.getDamage(730, 100, 19), 73);
        assertEquals(Main.getDamage(400, 80, 30), 0);
        assertEquals(Main.getDamage(400, 20, 30), 17);
        assertEquals(Main.getDamage(500, 20, 20), 28);
        assertEquals(Main.getDamage(200, 20, 10), 0);
    }

    @Test
    void isDeadByBodyShot() {
        assertFalse(Main.isDeadByBodyShot(100, 50));
        assertTrue(Main.isDeadByBodyShot(45, 50));
        assertTrue(Main.isDeadByBodyShot(1, 1));
        assertTrue(Main.isDeadByBodyShot(100, 100));
        assertFalse(Main.isDeadByBodyShot(100, 99));
        assertFalse(Main.isDeadByBodyShot(2, 1));
        assertTrue(Main.isDeadByBodyShot(15, 73));
    }

    @Test
    void getNumObjects() {
        assertEquals(Main.getNumObjects(map1), 7);
        assertEquals(Main.getNumObjects(map2), 0);
    }

    @Test
    void isHit() {
        assertTrue( Main.isHit(screenFloor, 3, 4));
        assertTrue( Main.isHit(screenLeft, 4, 0));
        assertTrue( Main.isHit(screenJump, 0, 6));
        assertFalse( Main.isHit(screenFloor, 0, 2));
        assertFalse( Main.isHit(screenLeft, 6, 3));
        assertFalse( Main.isHit(screenJump, 6, 6));
    }

    @Test
    void isHeadShot() {
        assertTrue( Main.isHeadShot(screenFloor, 2, 3));
        assertTrue( Main.isHeadShot(screenLeft, 2, 1));
        assertFalse( Main.isHeadShot(screenLeft, 2, 2));
        assertFalse( Main.isHeadShot(screenLeft, 2, 0));
        assertFalse( Main.isHeadShot(screenLeft, 3, 0));
        assertTrue( Main.isHeadShot(screenJump, 0, 6));
        assertFalse( Main.isHeadShot(screenFloor, 2, 2));
        assertFalse( Main.isHeadShot(screenLeft, 2, 5));
        assertFalse( Main.isHeadShot(screenJump, 2, 2));
    }

    @Test
    void getEnemyPosition() {
        int[] position1 = {4,3};
        assertArrayEquals(Main.getEnemyPosition(screenFloor), position1);
        int[] position2 = {4,1};
        assertArrayEquals(Main.getEnemyPosition(screenLeft), position2);
        int[] position3 = {2,6};
        assertArrayEquals(Main.getEnemyPosition(screenJump), position3);
        int[] position4 = {0,0};
        assertArrayEquals(Main.getEnemyPosition(screenNoEnemy), position4);
    }

}