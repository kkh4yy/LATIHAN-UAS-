import java.util.ArrayList;
import java.util.Scanner;

/**
 * Simulates a game of Crazy Eights.
 * See https://en.wikipedia.org/wiki/Crazy_Eights.
 */
public class Eights {

    private Player one;
    private Player two;
    private Player three;
    private Player four;
    private Player five;
    private Hand drawPile;
    private Hand discardPile;
    private Scanner in;
    private static ArrayList<Player> arrayPlayer = new ArrayList<>(); 

    /**
     * Initializes the state of the game.
     */
    public Eights(Player one, Player two, Player three, Player four, Player five) {
        Deck deck = new Deck("Deck");
        deck.shuffle();

        // deal cards to each player
        this.one = one;
        deck.deal(one.getHand(), 5);
        this.two = two;
        deck.deal(two.getHand(), 5);
        this.three = three;
        deck.deal(three.getHand(), 5);
        this.four = four;
        deck.deal(four.getHand(), 5);
        this.five = five;
        deck.deal(five.getHand(), 5);

        // turn one card face up
        discardPile = new Hand("Discards");
        deck.deal(discardPile, 1);

        // put the rest of the deck face down
        drawPile = new Hand("Draw pile");
        deck.dealAll(drawPile);

        // create the scanner 
        // we'll use to wait for the user to press enter
        in = new Scanner(System.in);
    }

    /**
     * Returns true if either hand is empty.
     */
    public boolean isDone() {
        return one.getHand().isEmpty() || two.getHand().isEmpty() || three.getHand().isEmpty() || four.getHand().isEmpty() || five.getHand().isEmpty();

    }

    /**
     * Moves cards from the discard pile to the draw pile and shuffles.
     */
    public void reshuffle() {
        // save the top card
        Card prev = discardPile.popCard();

        // move the rest of the cards
        discardPile.dealAll(drawPile);

        // put the top card back
        discardPile.addCard(prev);

        // shuffle the draw pile
        drawPile.shuffle();
    }

    /**
     * Returns a card from the draw pile.
     */
    public Card drawCard() {
        if (drawPile.isEmpty()) {
            reshuffle();
        }
        return drawPile.popCard();
    }

    /**
     * Switches players.
     */
    public Player nextPlayer(Player current) {
        if (current == one) {return two;} 
        else if (current == two) {return three;}
        else if (current == three) {return four;}
        else if (current == four) {return five;}
        else {return one;}
    }

    /**
     * Displays the state of the game.
     */
    public void displayState() {
        one.display();
        two.display();
        three.display();
        four.display();
        five.display();

        discardPile.display();
        System.out.println("Draw pile: " + drawPile.size() + " cards");
        in.nextLine();
    }

    /**
     * One player takes a turn.
     */
    public void takeTurn(Player player) {
        Scanner s = new Scanner(System.in);

        Card prev=null;

        if (player instanceof HumanPlayer){
            System.out.println("Pilih Ranks: "); //minta indexnya
            int Ranksnya = s.nextInt();
            s.nextLine();
            System.out.println("Pilih Suits:");  //minta indexnya
            int Suitsnya = s.nextInt();
            s.nextLine();

            prev = new Card(Ranksnya, Suitsnya);
        }
        else if (player instanceof AIPlayerLast) {
            prev = discardPile.lastCard();
        }
        else if (player instanceof AIPlayerAdvanced) {
            prev = player.searchForMax();
        }


        Card next = player.play(this, prev);


        discardPile.addCard(next);

        System.out.println(player.getName() + " plays " + next);
        System.out.println();
        
    }

    /**
     * Plays the game.
     */
    public void playGame() {
        Player player = one;

        // keep playing until there's a winner
        while (!isDone()) {
            displayState();
            takeTurn(player);
            player = nextPlayer(player);
        }

    }

    /**
     * Creates the game and runs it.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Mau main dengan berapa player: ");
        int jumlahpemain = input.nextInt();
        input.nextLine();

        for (int i = 0; i < jumlahpemain; i++) {
            System.out.println("Kamuy tuh apa?: ");
            String jenisMahkluk = input.nextLine();

            if (jenisMahkluk.equalsIgnoreCase("manusia")) {
                System.out.println("Nama kamu siapa?: ");
                String nama = input.nextLine();
                HumanPlayer manusia = new HumanPlayer(nama);
                arrayPlayer.add(manusia);

            }
            else if (jenisMahkluk.equalsIgnoreCase("ai")) {
                System.out.println("Kamu AI jenis apa: ");
                String AIapa = input.nextLine();
                if (AIapa.equalsIgnoreCase("aicanggih")) {
                    AIPlayerAdvanced AIcanggih = new AIPlayerAdvanced("BOT CANGGIH");
                    arrayPlayer.add(AIcanggih);
                }
                else if (AIapa.equalsIgnoreCase("aibiasa")) {
                    AIPlayerLast AIbiasa = new AIPlayerLast("BOT BIASA");
                    arrayPlayer.add(AIbiasa);
                }
            }


        }

        // Player one = new Player("Allen");
        // Player two = new Player("Chris");
        // Player three = new Player("Alif");
        // Player four = new Player("Hafiz");
        // Player five = new Player("Grady");

    
        System.out.println("Berapa kali kamu mau main: ");
        int bermain = input.nextInt();
        input.nextLine();

        int hitungmain = 0;
        while (hitungmain < bermain) {
            System.out.println(arrayPlayer.get(0).getName());
            Eights game = new Eights(arrayPlayer.get(0), arrayPlayer.get(1), arrayPlayer.get(2), arrayPlayer.get(3), arrayPlayer.get(4));
            game.playGame();
            hitungmain++;
        }

        for (int i = 0; i < arrayPlayer.size(); i++) {
            arrayPlayer.get(i).displayScore();
        }
        // display the final score
        // one.displayScore();
        // two.displayScore();
        // three.displayScore();
        // four.displayScore();
        // five.displayScore();

        input.close();
    }

}