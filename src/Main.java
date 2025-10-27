public class Main {
    public static void main(String[] args) {

    }

    public static int getDamage(int speed, int armor, int distance) {

        int damage;


        if (distance <= 3) {
            damage = speed / 10;
        } else if (speed > 500 && distance < 20) {
            damage = speed / 10;
        } else if (speed > 500 && distance > 20) {
            damage = (speed / 10) - armor;
        } else {
            damage = (speed / 10) - armor + (distance / 10);
        }

        if (damage < 0) {
            damage = 0;
        }

        return damage;
    }

    public static boolean isDeadByBodyShot(int life, int damage) {

        life -= damage;

        if (life <= 0) {
            return true;
        }

        return false;
    }

    public static int getNumObjects(boolean[][] map) {

        int cont = 0;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j]) {
                    cont++;
                }
            }
        }
        return cont;
    }

    public static boolean isHit(boolean[][] screen, int y, int x) {

        if (y < 0 || y >= screen.length || x < 0 || x >= screen[0].length) {
            return false;
        }

        // Retorna true se o disparo acertar um ponto "true" no mapa
        return screen[y][x];
    }


    public static boolean isHeadShot(boolean[][] screen, int y, int x) {

        if (y < 0 || y >= screen.length || x < 0 || x >= screen[0].length) {
            return false;
        }
        if (y == 0 && screen[y][x]) {
            return true;
        }

        return false;
    }

    public static int[] getEnemyPosition(boolean[][] screen) {

        for (int i = 0; i < screen.length; i++) {
            for (int j = 0; j < screen[i].length; j++) {
                if (screen[i][j]) { // encontrou o canto superior esquerdo do inimigo
                    int centerY = i + 1; // linha central do bloco 3x5
                    int centerX = j + 2; // coluna central do bloco 3x5
                    return new int[]{centerY, centerX};
                }
            }
        }
        // nenhum inimigo encontrado
        return new int[]{0, 0};
    }
}

