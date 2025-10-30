public class Main {
    public static void main(String[] args) {

    }

  /*  1. getDamage(int speed, int armor, int distance) : return int --> Esta función debe devolver el daño que
  ha recibido el personaje. La lógica será la siguiente.

	- Si la distancia es inferior o igual a 3 metros se considera disparo cercano (ignora armor). Damage = (speed/10)
    - Si la velocidad de la bala es superior a 500 m/s y la distancia inferior a 20 metros se considera disparo
             cercano (ignora armor). Damage = (speed/10)
   - Si la velocidad de la bala es superior a 500 m/s y la distancia es superior a 20 metros, se aplica la siguiente fórmula ((speed/10) - armor)
   - Para todos los demás casos ((speed/10) - armor+(distance/10) )
       NOTA: Si el damage es negativo, se devuelve 0.*/

    public static int getDamage(int speed, int armor, int distance) {

        int damage;


        if (distance <= 3) {
            damage = speed / 10;
        } else if (speed > 500 && distance < 20) {
            damage = speed / 10;
        } else if (speed > 500 && distance > 20) {
            damage = (speed / 10) - armor;
        } else {
            damage = (speed / 10) - armor - (distance / 10);
        }

        if (damage < 0) {
            damage = 0;
        }

        return damage;
    }
    /*2. isDeadByBodyShot(int life, int damage) : return boolean --> Esta función simplemente resta el daño recibido a
     la vida que le queda al personaje. Si al hacer la resta la vida es 0 o inferior, el personaje muere.
     */

    public static boolean isDeadByBodyShot(int life, int damage) {

        life -= damage;

        if (life <= 0) {
            return true;
        }

        return false;
    }

    /*3. getNumObjects(boolean[][] map) : return int  --> Esta función se le pasa como parámetro un mapa de un escenario.
    Nuestra labor será la de devolver el número de objetos que se han encontrado en el escenario. El mapa es booleano,
     esto quiere decir que si es false en esa parte del mapa se encuentra paisaje sin relevancia, en cambio, si es true,
     en esa parte del mapa se encuentra un objeto con el que se puede interactuar.
     */

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
/*4. isHit(boolean[][] screen, int y, int x) : return boolean. --> Está función recibe como parámetros la pantalla
del jugador y donde ha disparado (coordenadas). La pantalla será parecida a la del ejemplo. Tendremos que devolver
si el objetivo ha recibido impacto o no.
 */
    public static boolean isHit(boolean[][] screen, int y, int x) {

        if (y < 0 || y >= screen.length || x < 0 || x >= screen[0].length) {
            return false;
        }

        // Retorna true se o disparo acertar um ponto "true" no mapa
        return screen[y][x];
    }

/*5. isHeadShot(boolean[][] screen, int y, int x) : return boolean --> Exactamente igual que la anterior, pero en
este caso debemos saber si la posición en la que dispara es la columna central de la primera fila del enemigo.
En ese caso, el disparo sería en la cabeza y devolveríamos un true.
 */
public static boolean isHeadShot(boolean[][] screen, int x, int y) {

    // ------------------------------------------------------
    // 1️⃣ Checa se o tiro está muito próximo das bordas da tela
    //    Porque logo depois vamos olhar y-1 (à esquerda) e y+1 (à direita)
    //    Se o tiro estiver na coluna 0 ou na última coluna, não podemos olhar os vizinhos
    // ------------------------------------------------------
    if (y - 1 < 0 || screen[0].length - 1 < y + 1) return false;

    // ------------------------------------------------------
    // 2️⃣ Verifica se não tem inimigo acima do tiro
    //    - x == 0 → o tiro está na primeira linha da tela (não há nada acima)
    //    - !screen[x - 1][y] → quadradinho acima está vazio
    //    Isso garante que o tiro está na primeira linha do inimigo (cabeça)
    // ------------------------------------------------------
    boolean noTengoNadaArriba = x == 0 || !screen[x - 1][y];

    // ------------------------------------------------------
    // 3️⃣ Verifica se o tiro acertou a cabeça do inimigo
    //    Condições:
    //    a) screen[x][y] → o tiro acertou o quadradinho atual
    //    b) screen[x][y - 1] → há inimigo à esquerda (parte da primeira linha do inimigo)
    //    c) screen[x][y + 1] → há inimigo à direita (parte da primeira linha do inimigo)
    //    d) noTengoNadaArriba → não tem nada acima (primeira linha do inimigo)
    //
    //    Se todas forem verdadeiras → tiro na **cabeça**
    // ------------------------------------------------------
    return screen[x][y] && screen[x][y - 1] && screen[x][y + 1] && noTengoNadaArriba;
}

//        public static boolean isHeadShot (boolean [][] screen, int y, int x){
//            if (screen[y][x - 1] && screen[y][x + 1]){
//                for ( int i = y; i < y + 4; i++){
//                    if (!screen[i][x])
//                        return false;
//                }
//            }else
//                return false;
//            return true;
//        }
//    }
/*6. getEnemyPosition(boolean[][] screen) : return int[2]  --> Esta función se le pasa como parámetro la pantalla del
jugador cuando está en combate. En cada instante solo puede haber un enemigo ¿Como saber la posición del enemigo en la
pantalla? Los enemigos siempre ocuparán un espacio de 3x5 de tal manera que se tiene que devolver el centro de la figura. La función devolverá: Y,X. return ejemplo: 4, 3
NOTA: Si no existe enemigo en esos momentos, devolverá 0 0*/

    public static int[] getEnemyPosition(boolean[][] screen) {

        for (int i = 0; i < screen.length; i++) {   // percorre a linha
            for (int j = 0; j < screen[0].length; j++) {   // percorre a coluna
                if (screen[i][j]) {                            // si linha igual coluna
                    return new int[]{i + 2, j + 1};            // retorne linha + 2 (diz no enunciado), j

                }
            }
        }

        return new int[]{0, 0};
    }
}

