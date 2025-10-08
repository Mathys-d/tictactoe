package Game;

public class InteractionUtilisateur {
    public InteractionUtilisateur() {

    }

    public int[] getMoveFromPlayer() {
        boolean isValid = false;

        while (!isValid) {
            System.out.println("choose the row [0-2]");
            String first = sc.nextLine().trim();
            System.out.println("choose the column [0-2]");
            String second = sc.nextLine().trim();
            System.out.println("you choose  " + first + " and " + second);

            if (Integer.parseInt(first) >= 0 && Integer.parseInt(first) <= 2) {
                if (Integer.parseInt(second) >= 0 && Integer.parseInt(second) <= 2) {
                    if (!isUsed(Integer.parseInt(first), Integer.parseInt(second))) {
                        return new int[]{Integer.parseInt(first), Integer.parseInt(second)};
                    } else {
                        System.out.println("retry");
                    }
                } else {
                    System.out.println("second value is not an integer");
                }
            } else {
                System.out.println("first value is not an integer");
            }
        }
        System.out.println("out of the while");
        return null;
    }

    public int[] getMoveFromArtificial() {
        int row, col;
        do {
            row = randomNumbers.nextInt(3);
            col = randomNumbers.nextInt(3);
        } while (isUsed(row, col));

        System.out.println("IA chooses " + row + " and " + col);
        return new int[]{row, col};
    }

    public String getRandomNumber() {
        int nb = randomNumbers.nextInt(3);
        return String.valueOf(nb);
    }
}
