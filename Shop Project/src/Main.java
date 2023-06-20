import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("\n======================== WELCOME TO THE ELECTRONICS STORE! ========================");
        whichOption();
    }

    //================================ SEKCJA WYBORU ================================
    //tutaj jest początkowe menu, gdzie dokonuje się wyboru kategori zakupu, bądź panelu admina
    public static void whichOption()
    {
        menu();
        System.out.print("What shopping do you choose? :");
        int choiceShopping = inputInt();
        switch (choiceShopping)
        {
            case 1:
                option1();
                break;
            case 2:
                option2();
                break;
            case 3:
                break;
            case 4:
                System.exit(1);
                break;
            default:
                System.out.println("\nEnter correct number!");
                whichOption();
                break;
        }
    }

    //VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV
    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv SEKCJA ZAKUPÓW DETALICZNYCH vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
    //VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV

    public static void option1()
    {
        System.out.println("\n======================== RETAIL SHOPPING ========================");
        System.out.println("Remember that as a retail customer you can buy a maximum of 5 items!");
        while(true)
        {
            menuProducts();
            System.out.print("What do you want to do?: ");
            int choiceOption = inputInt(); System.out.println();
            switch (choiceOption)
            {
                case 1:
                    DataBase showPc = new DataBase("SELECT * from jdbc.pc");
                    showPc.showPc(); System.out.println();
                    buyingPC();

                    break;
                case 2:
                    DataBase laptopShow = new DataBase("SELECT * from jdbc.laptop");
                    laptopShow.showLaptop();
                    buyingLaptop();
                    break;
                case 3:
                    DataBase tabletShow = new DataBase("SELECT * from jdbc.tablet");
                    tabletShow.showTablet();
                    buyingTablet();
                    break;
                case 4:
                    DataBase smartphoneShow = new DataBase("SELECT * from jdbc.smartphone");
                    smartphoneShow.showSmartphone();
                    buyingSmartphone();
                    break;
                case 5:
                    DataBase monitorShow = new DataBase("SELECT * from jdbc.monitor");
                    monitorShow.showMonitor();
                    buyingMonitor();
                    break;
                case 6:
                    DataBase keyboardShow = new DataBase("SELECT * from jdbc.keyboard");
                    keyboardShow.showKeyboard();
                    buyingKeyboard();
                    break;
                case 7:
                    DataBase mouseShow = new DataBase("SELECT * from jdbc.mouse");
                    mouseShow.showMouse();
                    buyingMouse();
                    break;
                case 8:
                    shoppingCartTotal();

                    break;
                case 9:
                    whichOption();
                default:
                    System.out.println("Enter correct number!\n");
            }
        }
    }





    //================================ SEKCJA KOSZYKA, ZAKUPU Z STWORZONEGO KOSZYKA ================================
    //======================================= DLA KOMPUTERÓW STACJONARNYCH ========================================
    //sekcja globalnych danych żeby każdy je widział
    static int sum = 0,money;
    static int[] quantityPc = {0,0,0,0,0};
    static int[] quantityLaptop = {0,0,0,0,0};
    static int[] quantityTablet = {0,0,0,0,0};
    static int[] quantitySmartphone = {0,0,0,0,0};
    static int[] quantityMonitor = {0,0,0,0,0};
    static int[] quantityKeyboard = {0,0,0,0,0};
    static int[] quantityMouse = {0,0,0,0,0};
    public static void shoppingCartPC()
    {
        if(pcOnList.isEmpty() && laptopOnList.isEmpty() && tabletOnList.isEmpty() && smartphoneOnList.isEmpty() && monitorOnList.isEmpty() && keyboardOnList.isEmpty() && mouseOnList.isEmpty())
        {
            x =0;
            System.out.println("Your shopping cart is empty!");
        }
        else
        {
            //wpłacanie hajsu
            System.out.print("Enter the amount you are paying into your wallet: ");
            money = inputInt();

            //obliczanie sumy kwoty za komputery i zmiana ilości magazynowej
            for(int j=0; j< pcOnList.size(); j++)
            {
                sum = sum + shoppingCartSum(pcOnList.get(j), "pc","PC");
                quantityCountPc(j);
            }
        }
    }
    //Liczenie ilosci dla komputerow
    public static void quantityCountPc(int j)
    {
        if(pcOnList.get(j)==1)
        {
            quantityPc[0]++;
        }
        else if(pcOnList.get(j)==2)
        {
            quantityPc[1]++;
        }
        else if(pcOnList.get(j)==3)
        {
            quantityPc[2]++;
        }
        else if(pcOnList.get(j)==4)
        {
            quantityPc[3]++;
        }
        else
        {
            quantityPc[4]++;
        }
    }

    //Wykonywanie sumowania ceny produktu, ma być w pętli
    public static int shoppingCartSum(Integer option, String option2,String option3)
    {
        int sumMoney = 0;
        DataBase priceCheck = new DataBase("SELECT price FROM jdbc."+option2+" WHERE id"+option3+" = "+option+";");
        sumMoney = sumMoney + priceCheck.totalPrice();
        return sumMoney;
    }
    public static int shoppingCartQuantityUpdate(Integer option,String option2,String option3, int option4)
    {
        DataBase quantityUpdate = new DataBase("UPDATE jdbc."+option2+" SET quantity ="+option4+" WHERE id"+option3+" ="+option);
        int quantityUpdating = quantityUpdate.updateQuantity();
        return quantityUpdating;
    }
    public static int shoppingCartQuantityCheck(Integer option, String option2,String option3)
    {
        DataBase quantityCheck = new DataBase("SELECT quantity FROM jdbc."+option2+" WHERE id"+option3+" = "+option+";");
        int quantityActual = quantityCheck.totalQuantity();
        return quantityActual;
    }


    //================================ SEKCJA KOSZYKA, ZAKUPU Z STWORZONEGO KOSZYKA ================================
    //=============================================== DLA LAPTOPÓW =================================================
    public static void shoppingCartLaptop()
    {
        for(int j=0; j< laptopOnList.size(); j++)
        {
            sum = sum + shoppingCartSum(laptopOnList.get(j), "laptop","Laptop");
            quantityCountLaptop(j);
        }
    }
    public static void quantityCountLaptop(int j)
    {
        if(laptopOnList.get(j)==1)
        {
            quantityLaptop[0]++;
        }
        else if(laptopOnList.get(j)==2)
        {
            quantityLaptop[1]++;
        }
        else if(laptopOnList.get(j)==3)
        {
            quantityLaptop[2]++;
        }
        else if(laptopOnList.get(j)==4)
        {
            quantityLaptop[3]++;
        }
        else
        {
            quantityLaptop[4]++;
        }
    }

    //================================ SEKCJA KOSZYKA, ZAKUPU Z STWORZONEGO KOSZYKA ================================
    //=============================================== DLA TABLETÓW =================================================
    public static void shoppingCartTablet()
    {
        for(int j=0; j< tabletOnList.size(); j++)
        {
            sum = sum + shoppingCartSum(tabletOnList.get(j), "tablet","tablet");
            quantityCountTablet(j);
        }
    }
    public static void quantityCountTablet(int j)
    {
        if(tabletOnList.get(j)==1)
        {
            quantityTablet[0]++;
        }
        else if(tabletOnList.get(j)==2)
        {
            quantityTablet[1]++;
        }
        else if(tabletOnList.get(j)==3)
        {
            quantityTablet[2]++;
        }
        else if(tabletOnList.get(j)==4)
        {
            quantityTablet[3]++;
        }
        else
        {
            quantityTablet[4]++;
        }
    }

    //================================ SEKCJA KOSZYKA, ZAKUPU Z STWORZONEGO KOSZYKA ================================
    //============================================ DLA SMARTPHONE ==================================================
    public static void shoppingCartSmartphone()
    {
        for(int j=0; j< smartphoneOnList.size(); j++)
        {
            sum = sum + shoppingCartSum(smartphoneOnList.get(j), "smartphone","smartphone");
            quantityCountSmartpone(j);
        }
    }
    public static void quantityCountSmartpone(int j)
    {
        if(smartphoneOnList.get(j)==1)
        {
            quantitySmartphone[0]++;
        }
        else if(smartphoneOnList.get(j)==2)
        {
            quantitySmartphone[1]++;
        }
        else if(smartphoneOnList.get(j)==3)
        {
            quantitySmartphone[2]++;
        }
        else if(smartphoneOnList.get(j)==4)
        {
            quantitySmartphone[3]++;
        }
        else
        {
            quantitySmartphone[4]++;
        }
    }

    //================================ SEKCJA KOSZYKA, ZAKUPU Z STWORZONEGO KOSZYKA ================================
    //============================================ DLA MONITORÓW ==================================================
    public static void shoppingCartMonitor()
    {
        for(int j=0; j< monitorOnList.size(); j++)
        {
            sum = sum + shoppingCartSum(monitorOnList.get(j), "monitor","monitor");
            quantityCountMonitor(j);
        }
    }
    public static void quantityCountMonitor(int j)
    {
        if(monitorOnList.get(j)==1)
        {
            quantityMonitor[0]++;
        }
        else if(monitorOnList.get(j)==2)
        {
            quantityMonitor[1]++;
        }
        else if(monitorOnList.get(j)==3)
        {
            quantityMonitor[2]++;
        }
        else if(monitorOnList.get(j)==4)
        {
            quantityMonitor[3]++;
        }
        else
        {
            quantityMonitor[4]++;
        }
    }

    //================================ SEKCJA KOSZYKA, ZAKUPU Z STWORZONEGO KOSZYKA ================================
    //============================================ DLA KLAWIATUR ===================================================
    public static void shoppingCartKeyboard()
    {
        for(int j=0; j< keyboardOnList.size(); j++)
        {
            sum = sum + shoppingCartSum(keyboardOnList.get(j), "keyboard","keyboard");
            quantityCountKeyboard(j);
        }
    }
    public static void quantityCountKeyboard(int j)
    {
        if(keyboardOnList.get(j)==1)
        {
            quantityKeyboard[0]++;
        }
        else if(keyboardOnList.get(j)==2)
        {
            quantityKeyboard[1]++;
        }
        else if(keyboardOnList.get(j)==3)
        {
            quantityKeyboard[2]++;
        }
        else if(keyboardOnList.get(j)==4)
        {
            quantityKeyboard[3]++;
        }
        else
        {
            quantityKeyboard[4]++;
        }
    }

    //================================ SEKCJA KOSZYKA, ZAKUPU Z STWORZONEGO KOSZYKA ================================
    //============================================ DLA MYSZEK ===================================================
    public static void shoppingCartMouse()
    {
        for(int j=0; j< mouseOnList.size(); j++)
        {
            sum = sum + shoppingCartSum(keyboardOnList.get(j), "mouse","mouse");
            quantityCountMouse(j);
        }
    }
    public static void quantityCountMouse(int j)
    {
        if(mouseOnList.get(j)==1)
        {
            quantityMouse[0]++;
        }
        else if(mouseOnList.get(j)==2)
        {
            quantityMouse[1]++;
        }
        else if(mouseOnList.get(j)==3)
        {
            quantityMouse[2]++;
        }
        else if(mouseOnList.get(j)==4)
        {
            quantityMouse[3]++;
        }
        else
        {
            quantityMouse[4]++;
        }
    }


    //================================ SEKCJA KOSZYKA, ZAKUPU Z STWORZONEGO KOSZYKA ================================
    //============================================ PODSUMOWUJĄCA =======================================
    public static void shoppingCartTotal()
    {
        //wywołanie funkcji liczących cenę i ilość przedmiotów
        shoppingCartPC();
        shoppingCartLaptop();
        shoppingCartTablet();
        shoppingCartSmartphone();
        shoppingCartMonitor();
        shoppingCartKeyboard();
        shoppingCartMouse();
        if((money-sum)<0 || money==0)
        {
            System.out.println("Your order has been canceled! :(\n");
            pcOnList.clear();
        }
        else
        {
            System.out.println("THE ORDER HAS BEEN SENT :)");
            //pętla do aktualizacji produktów
            for(int j=0; j<5;j++)
            {
                //pobranie akutalnej ilosci komputerow i wprowadzenie do bazy
                int quantityActualPc = shoppingCartQuantityCheck(j+1, "pc","PC");
                int quantityNewPc = shoppingCartQuantityUpdate(j+1,"pc","PC", (quantityActualPc- quantityPc[j]));

                //pobieranie akutalnej ilosci laptopow i wprowadzenie do bazy
                int quantityActualLaptop = shoppingCartQuantityCheck(j+1, "laptop","Laptop");
                int quantityNewLaptop = shoppingCartQuantityUpdate(j+1, "laptop", "Laptop", (quantityActualLaptop-quantityLaptop[j]));

                //pobieranie akutalnej ilosci tabletow i wprowadzenie do bazy
                int quantityAcutalTablet = shoppingCartQuantityCheck(j+1,"tablet","tablet");
                int quantityNewTablet = shoppingCartQuantityUpdate(j+1, "tablet", "tablet",(quantityAcutalTablet-quantityTablet[j]));

                //pobieranie akutalnej ilosci telefonow i wprowadzenie do bazy
                int quantityActualSmartphone = shoppingCartQuantityCheck(j+1, "smartphone","smartphone");
                int quantityNewSmartphone = shoppingCartQuantityUpdate(j+1, "smartphone","smartphone",(quantityActualSmartphone-quantitySmartphone[j]));

                //pobieranie akutalnej ilosci monitorow i wprowadzenie do bazy
                int quantityActualMonitor = shoppingCartQuantityCheck(j+1, "monitor", "monitor");
                int quantityNewMonitor = shoppingCartQuantityUpdate(j+1, "monitor", "monitor", (quantityActualMonitor-quantityMonitor[j]));

                //pobieranie akutalnej ilosci klawiatur i wprowadzenie do bazy
                int quantityActualKeyboard = shoppingCartQuantityCheck(j+1, "keyboard", "keyboard");
                int quantityNewKeyboard = shoppingCartQuantityUpdate(j+1, "keyboard", "keyboard", (quantityActualKeyboard-quantityKeyboard[j]));

                //pobieranie akutalnej ilosci mysz i wprowadzenie do bazy
                int quantityActualMouse = shoppingCartQuantityCheck(j+1, "mouse", "mouse");
                int quantityNewMouse = shoppingCartQuantityUpdate(j+1, "mouse", "mouse", (quantityActualMouse-quantityMouse[j]));
            }
        }
        //czyszczenie listy po zakupach
        pcOnList.clear();

    }


    //================================ SEKCJA DODAWANIA DO KOSZYKA, WIELOKROTNEGO ZAKUPU ================================
    // ============== TUTAJ DODAJEMY PRODUKTY DO KOSZYKA, KLIENT WYBIERA CO CHCE UMIEŚCIĆ NA LIŚCIE A CO NIE ============

    //koszyki zakupowe listy, zrobione tak aby widoczne były dla każdej metody
    public static ArrayList<Integer> pcOnList = new ArrayList<>();
    public static ArrayList<Integer> laptopOnList = new ArrayList<>();
    public static ArrayList<Integer> tabletOnList = new ArrayList<>();
    public static ArrayList<Integer> smartphoneOnList = new ArrayList<>();
    public static ArrayList<Integer> monitorOnList = new ArrayList<>();
    public static ArrayList<Integer> keyboardOnList = new ArrayList<>();
    public static ArrayList<Integer> mouseOnList = new ArrayList<>();
    //zmienna x, ktora odpowiada za maksymalna ilosc rzeczy jako detaliczny klient moze kupic
    public static int x = 0;

    //sekcja do wielokrotnego dodawania do koszyka komputerów
    public static void buyingPC()
    {
        while (true)
        {
            System.out.print("Add to shopping cart? (yes/no): ");
            String choiceBuyOrNot = inputString();
            //sprawdzamy w ifie czy przypadkiem klient nie ma juz max towarow w koszyku
            if(x !=5)
            {
                if(choiceBuyOrNot.equals("yes"))
                {
                    System.out.print("Which option do you want to buy?:");
                    int choiceBuy = inputInt();
                    switch (choiceBuy)
                    {
                        case 1:
                            System.out.println("Computer number 1 has been added to the cart!\n");
                            pcOnList.add(1);
                            break;
                        case 2:
                            System.out.println("Computer number 2 has been added to the cart!\n");
                            pcOnList.add(2);
                            break;
                        case 3:
                            System.out.println("Computer number 3 has been added to the cart!\n");
                            pcOnList.add(3);
                            break;
                        case 4:
                            System.out.println("Computer number 4 has been added to the cart!\n");
                            pcOnList.add(4);
                            break;
                        case 5:
                            System.out.println("Computer number 5 has been added to the cart!\n");
                            pcOnList.add(5);
                            break;
                        default:
                            System.out.print("Enter correct number!\n");
                    }
                    //zwiekszamy x po dodaniu towaru do koszyka
                    x++;
                }
                else
                {
                    System.out.println();
                    break;
                }
            }
            else
            {
                //wywala nam zakup jesli juz klient detaliczny ma max produktow
                System.out.println("you have added 5 items to your cart, you cannot add more!\n");
                break;
            }

        }
    }
    //sekcja do wielokrotnego dodawania do koszyka laptopów
    public static void buyingLaptop()
    {
        while (true)
        {
            System.out.print("Add to shopping cart? (yes/no): ");
            String choiceBuyOrNot = inputString();
            if(x !=5)
            {
                if(choiceBuyOrNot.equals("yes"))
                {
                    System.out.print("Which option do you want to buy?:");
                    int choiceBuy = inputInt();
                    switch (choiceBuy)
                    {
                        case 1:
                            System.out.println("Laptop number 1 has been added to the cart!\n");
                            laptopOnList.add(1);
                            break;
                        case 2:
                            System.out.println("Laptop  number 2 has been added to the cart!\n");
                            laptopOnList.add(2);
                            break;
                        case 3:
                            System.out.println("Laptop  number 3 has been added to the cart!\n");
                            laptopOnList.add(3);
                            break;
                        case 4:
                            System.out.println("Laptop  number 4 has been added to the cart!\n");
                            laptopOnList.add(4);
                            break;
                        case 5:
                            System.out.println("Laptop  number 5 has been added to the cart!\n");
                            laptopOnList.add(5);
                            break;
                        default:
                            System.out.print("Enter correct number!\n");
                    }
                    x++;
                }
                else
                {
                    System.out.println();
                    break;
                }
            }
            else
            {
                System.out.println("you have added 5 items to your cart, you cannot add more!\n");
                break;
            }

        }
    }
    //sekcja do wielokrotnego dodawania do koszyka tabletow
    public static void buyingTablet()
    {
        while (true)
        {
            System.out.print("Add to shopping cart? (yes/no): ");
            String choiceBuyOrNot = inputString();
            if(x !=5)
            {
                if(choiceBuyOrNot.equals("yes"))
                {
                    System.out.print("Which option do you want to buy?:");
                    int choiceBuy = inputInt();
                    switch (choiceBuy)
                    {
                        case 1:
                            System.out.println("Tablet number 1 has been added to the cart!\n");
                            tabletOnList.add(1);
                            break;
                        case 2:
                            System.out.println("Tablet  number 2 has been added to the cart!\n");
                            tabletOnList.add(2);
                            break;
                        case 3:
                            System.out.println("Tablet  number 3 has been added to the cart!\n");
                            tabletOnList.add(3);
                            break;
                        case 4:
                            System.out.println("Tablet  number 4 has been added to the cart!\n");
                            tabletOnList.add(4);
                            break;
                        case 5:
                            System.out.println("Tablet  number 5 has been added to the cart!\n");
                            tabletOnList.add(5);
                            break;
                        default:
                            System.out.print("Enter correct number!\n");
                    }
                }
                else
                {
                    System.out.println();
                    break;
                }
                x++;
            }
            else
            {
                System.out.println("you have added 5 items to your cart, you cannot add more!\n");
                break;
            }

        }
    }
    //sekcja do wielokrotnego dodawania do koszyka monitorow
    public static void buyingMonitor()
    {
        while (true)
        {
            System.out.print("Add to shopping cart? (yes/no): ");
            String choiceBuyOrNot = inputString();
            if(x !=5)
            {
                if(choiceBuyOrNot.equals("yes"))
                {
                    System.out.print("Which option do you want to buy?:");
                    int choiceBuy = inputInt();
                    switch (choiceBuy)
                    {
                        case 1:
                            System.out.println("Monitor number 1 has been added to the cart!\n");
                            monitorOnList.add(1);
                            break;
                        case 2:
                            System.out.println("Monitor  number 2 has been added to the cart!\n");
                            monitorOnList.add(2);
                            break;
                        case 3:
                            System.out.println("Monitor  number 3 has been added to the cart!\n");
                            monitorOnList.add(3);
                            break;
                        case 4:
                            System.out.println("Monitor  number 4 has been added to the cart!\n");
                            monitorOnList.add(4);
                            break;
                        case 5:
                            System.out.println("Monitor  number 5 has been added to the cart!\n");
                            monitorOnList.add(5);
                            break;
                        default:
                            System.out.print("Enter correct number!\n");
                    }
                }
                else
                {
                    System.out.println();
                    break;
                }
                x++;
            }
            else
            {
                System.out.println("you have added 5 items to your cart, you cannot add more!\n");
                break;
            }
        }
    }
    //sekcja do wielokrotnego dodawania do koszyka telefonow
    public static void buyingSmartphone()
    {
        while (true)
        {
            System.out.print("Add to shopping cart? (yes/no): ");
            String choiceBuyOrNot = inputString();
            if(x !=5)
            {
                if(choiceBuyOrNot.equals("yes"))
                {
                    System.out.print("Which option do you want to buy?:");
                    int choiceBuy = inputInt();
                    switch (choiceBuy)
                    {
                        case 1:
                            System.out.println("Smartphone number 1 has been added to the cart!\n");
                            smartphoneOnList.add(1);
                            break;
                        case 2:
                            System.out.println("Smartphone  number 2 has been added to the cart!\n");
                            smartphoneOnList.add(2);
                            break;
                        case 3:
                            System.out.println("Smartphone  number 3 has been added to the cart!\n");
                            smartphoneOnList.add(3);
                            break;
                        case 4:
                            System.out.println("Smartphone  number 4 has been added to the cart!\n");
                            smartphoneOnList.add(4);
                            break;
                        case 5:
                            System.out.println("Smartphone  number 5 has been added to the cart!\n");
                            smartphoneOnList.add(5);
                            break;
                        default:
                            System.out.print("Enter correct number!\n");
                    }
                }
                else
                {
                    System.out.println();
                    break;
                }
                x++;
            }
            else
            {
                System.out.println("you have added 5 items to your cart, you cannot add more!\n");
                break;
            }
        }
    }

    //sekcja do wielokrotnego dodawania do koszyka klawiatur
    public static void buyingKeyboard()
    {
        while (true)
        {
            System.out.print("Add to shopping cart? (yes/no): ");
            String choiceBuyOrNot = inputString();
            if(x !=5)
            {
                if(choiceBuyOrNot.equals("yes"))
                {
                    System.out.print("Which option do you want to buy?:");
                    int choiceBuy = inputInt();
                    switch (choiceBuy)
                    {
                        case 1:
                            System.out.println("Keyboard number 1 has been added to the cart!\n");
                            keyboardOnList.add(1);
                            break;
                        case 2:
                            System.out.println("Keyboard number 2 has been added to the cart!\n");
                            keyboardOnList.add(2);
                            break;
                        case 3:
                            System.out.println("Keyboard number 3 has been added to the cart!\n");
                            keyboardOnList.add(3);
                            break;
                        case 4:
                            System.out.println("Keyboard number 4 has been added to the cart!\n");
                            keyboardOnList.add(4);
                            break;
                        case 5:
                            System.out.println("Keyboard number 5 has been added to the cart!\n");
                            keyboardOnList.add(5);
                            break;
                        default:
                            System.out.print("Enter correct number!\n");
                    }
                }
                else
                {
                    System.out.println();
                    break;
                }
                x++;
            }
            else
            {
                System.out.println("you have added 5 items to your cart, you cannot add more!\n");
                break;
            }
        }
    }
    //sekcja do wielokrotnego dodawania do koszyka myszek
    public static void buyingMouse()
    {
        while (true)
        {
            if(x !=5)
            {
                System.out.print("\nAdd to shopping cart? (yes/no): ");
                String choiceBuyOrNot = inputString();
                if(choiceBuyOrNot.equals("yes"))
                {
                    System.out.print("Which option do you want to buy?:");
                    int choiceBuy = inputInt();
                    switch (choiceBuy)
                    {
                        case 1:
                            System.out.println("Mouse number 1 has been added to the cart!\n");
                            mouseOnList.add(1);
                            break;
                        case 2:
                            System.out.println("Mouse number 2 has been added to the cart!\n");
                            mouseOnList.add(2);
                            break;
                        case 3:
                            System.out.println("Mouse number 3 has been added to the cart!\n");
                            mouseOnList.add(3);
                            break;
                        case 4:
                            System.out.println("Mouse number 4 has been added to the cart!\n");
                            mouseOnList.add(4);
                            break;
                        case 5:
                            System.out.println("Mouse number 5 has been added to the cart!\n");
                            mouseOnList.add(5);
                            break;
                        default:
                            System.out.print("Enter correct number!\n");
                    }
                }
                else
                {
                    System.out.println();
                    break;
                }
                x++;
            }
            else
            {
                System.out.println("you have added 5 items to your cart, you cannot add more!\n");
                break;
            }

        }
    }
    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ SEKCJA ZAKUPÓW DETALICZNYCH ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^







    //VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV
    //VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV SEKCJA ZAKUPÓW HURTOWYCH VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV
    //VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV

    //stworzenie listy w ktorych sa trzymane produkty do koszyka tak zeby byly widoczne fest
    public static ArrayList<Integer> pcOnListLog = new ArrayList<>();
    public static ArrayList<Integer> laptopOnListLog = new ArrayList<>();
    public static ArrayList<Integer> tabletOnListLog = new ArrayList<>();
    public static ArrayList<Integer> smartphoneOnListLog = new ArrayList<>();
    public static ArrayList<Integer> monitorOnListLog = new ArrayList<>();
    public static ArrayList<Integer> keyboardOnListLog = new ArrayList<>();
    public static ArrayList<Integer> mouseOnListLog = new ArrayList<>();
    public static String login, password;
    public static int sumLog = 0, moneyLog;

    //=========================================== SEKCJA LOGOWANIA, SPRAWDZANIA DANYCH ===========================
    public static void option2()
    {
        System.out.println("\n\n======================== WHOLESALE SHOPPING ========================");
        for(int j=0; j<3; j++)
        {
            System.out.print("Enter login: ");
            login = inputString();
            System.out.print("Enter password: ");
            password = inputString();

            DataBase loginUser = new DataBase("SELECT login,password FROM jdbc.customers where login = '"+login+"' AND password = '"+password+"';");
            boolean loginAccept = loginUser.login();
            if(loginAccept == true)
            {
                loginSuccessful();
            }
            else
            {
                System.out.println("\nIncorrect login details!");
            }
        }
    }

    //=========================================== JEŚLI ZALOGOWANO POMYŚLNIE ==================================
    //====================================== DOSTAJESZ DOSTĘP DO PANELU ZAKUPOWEGO ============================
    public static void loginSuccessful()
    {
        System.out.println("Hi "+login+", as a logged in customer you have unlimited purchases!");
        while(true)
        {
            System.out.println();
            menuProducts();
            System.out.print("What do you want to do?: ");
            int choiceOption = inputInt(); System.out.println();
            switch (choiceOption)
            {
                case 1:
                    DataBase showPc = new DataBase("SELECT * from jdbc.pc");
                    showPc.showPc(); System.out.println();
                    buyingPCLog();
                    break;
                case 2:
                    DataBase laptopShow = new DataBase("SELECT * from jdbc.laptop");
                    laptopShow.showLaptop();
                    buyingLaptopLog();
                    break;
                case 3:
                    DataBase tabletShow = new DataBase("SELECT * from jdbc.tablet");
                    tabletShow.showTablet();
                    buyingTabletLog();
                    break;
                case 4:
                    DataBase smartphoneShow = new DataBase("SELECT * from jdbc.smartphone");
                    smartphoneShow.showSmartphone();
                    buyingSmartphoneLog();
                    break;
                case 5:
                    DataBase monitorShow = new DataBase("SELECT * from jdbc.monitor");
                    monitorShow.showMonitor();
                    buyingMonitorLog();
                    break;
                case 6:
                    DataBase keyboardShow = new DataBase("SELECT * from jdbc.keyboard");
                    keyboardShow.showKeyboard();
                    buyingKeyboardLog();
                    break;
                case 7:
                    DataBase mouseShow = new DataBase("SELECT * from jdbc.mouse");
                    mouseShow.showMouse();
                    buyingMouseLog();
                    break;
                case 8:
                    shoppingCartTotalLog();
                    break;
                case 9:
                    whichOption();
                default:
                    System.out.println("Enter correct number!\n");
            }
        }
    }

    //==================================== SEKCJA KOSZYKA, SUMUJĄCA DLA KOMPUTERÓW =================================
    public static int shoppingCartPCLog()
    {
        if(pcOnListLog.isEmpty() && laptopOnListLog.isEmpty() && tabletOnListLog.isEmpty() && smartphoneOnListLog.isEmpty() && monitorOnListLog.isEmpty() && keyboardOnListLog.isEmpty() && mouseOnListLog.isEmpty())
        {
            System.out.println("Your shopping cart is empty!");
        }
        else
        {
            //wpłacanie hajsu
            DataBase walletCheck = new DataBase("SELECT wallet FROM jdbc.customers where login='"+login+"';");
            moneyLog = walletCheck.walletUser();
            System.out.println("Your shopping funds: "+moneyLog);

            //obliczanie sumy kwoty za komputery i zmiana ilości magazynowej
            for(int j=0; j< pcOnListLog.size(); j++)
            {
                sumLog = sumLog + shoppingCartSum(pcOnListLog.get(j), "pc","PC");
                quantityCountPcLog(j);
            }
        }
        return moneyLog;
    }
    //Liczenie ilosci dla komputerow
    public static void quantityCountPcLog(int j)
    {
        if(pcOnListLog.get(j)==1)
        {
            quantityPcLog[0]++;
        }
        else if(pcOnListLog.get(j)==2)
        {
            quantityPcLog[1]++;
        }
        else if(pcOnListLog.get(j)==3)
        {
            quantityPcLog[2]++;
        }
        else if(pcOnListLog.get(j)==4)
        {
            quantityPcLog[3]++;
        }
        else
        {
            quantityPcLog[4]++;
        }
    }
    //================================ SEKCJA KOSZYKA, ZAKUPU Z STWORZONEGO KOSZYKA ================================
    //=============================================== DLA LAPTOPÓW =================================================
    public static void shoppingCartLaptopLog()
    {
        for(int j=0; j< laptopOnListLog.size(); j++)
        {
            sumLog = sumLog + shoppingCartSum(laptopOnListLog.get(j), "laptop","Laptop");
            quantityCountLaptopLog(j);
        }
    }
    public static void quantityCountLaptopLog(int j)
    {
        if(laptopOnListLog.get(j)==1)
        {
            quantityLaptopLog[0]++;
        }
        else if(laptopOnListLog.get(j)==2)
        {
            quantityLaptopLog[1]++;
        }
        else if(laptopOnListLog.get(j)==3)
        {
            quantityLaptopLog[2]++;
        }
        else if(laptopOnListLog.get(j)==4)
        {
            quantityLaptopLog[3]++;
        }
        else
        {
            quantityLaptopLog[4]++;
        }
    }

    //================================ SEKCJA KOSZYKA, ZAKUPU Z STWORZONEGO KOSZYKA ================================
    //=============================================== DLA TABLETÓW =================================================
    public static void shoppingCartTabletLog()
    {
        for(int j=0; j< tabletOnListLog.size(); j++)
        {
            sumLog = sumLog + shoppingCartSum(tabletOnListLog.get(j), "tablet","tablet");
            quantityCountTabletLog(j);
        }
    }
    public static void quantityCountTabletLog(int j)
    {
        if(tabletOnListLog.get(j)==1)
        {
            quantityTabletLog[0]++;
        }
        else if(tabletOnListLog.get(j)==2)
        {
            quantityTabletLog[1]++;
        }
        else if(tabletOnListLog.get(j)==3)
        {
            quantityTabletLog[2]++;
        }
        else if(tabletOnListLog.get(j)==4)
        {
            quantityTabletLog[3]++;
        }
        else
        {
            quantityTabletLog[4]++;
        }
    }

    //================================ SEKCJA KOSZYKA, ZAKUPU Z STWORZONEGO KOSZYKA ================================
    //============================================ DLA SMARTPHONE ==================================================
    public static void shoppingCartSmartphoneLog()
    {
        for(int j=0; j< smartphoneOnListLog.size(); j++)
        {
            sumLog = sumLog + shoppingCartSum(smartphoneOnListLog.get(j), "smartphone","smartphone");
            quantityCountSmartponeLog(j);
        }
    }
    public static void quantityCountSmartponeLog(int j)
    {
        if(smartphoneOnListLog.get(j)==1)
        {
            quantitySmartphoneLog[0]++;
        }
        else if(smartphoneOnListLog.get(j)==2)
        {
            quantitySmartphoneLog[1]++;
        }
        else if(smartphoneOnListLog.get(j)==3)
        {
            quantitySmartphoneLog[2]++;
        }
        else if(smartphoneOnListLog.get(j)==4)
        {
            quantitySmartphoneLog[3]++;
        }
        else
        {
            quantitySmartphoneLog[4]++;
        }
    }

    //================================ SEKCJA KOSZYKA, ZAKUPU Z STWORZONEGO KOSZYKA ================================
    //============================================ DLA MONITORÓW ==================================================
    public static void shoppingCartMonitorLog()
    {
        for(int j=0; j< monitorOnListLog.size(); j++)
        {
            sumLog = sumLog + shoppingCartSum(monitorOnListLog.get(j), "monitor","monitor");
            quantityCountMonitorLog(j);
        }
    }
    public static void quantityCountMonitorLog(int j)
    {
        if(monitorOnListLog.get(j)==1)
        {
            quantityMonitorLog[0]++;
        }
        else if(monitorOnListLog.get(j)==2)
        {
            quantityMonitorLog[1]++;
        }
        else if(monitorOnListLog.get(j)==3)
        {
            quantityMonitorLog[2]++;
        }
        else if(monitorOnListLog.get(j)==4)
        {
            quantityMonitorLog[3]++;
        }
        else
        {
            quantityMonitorLog[4]++;
        }
    }

    //================================ SEKCJA KOSZYKA, ZAKUPU Z STWORZONEGO KOSZYKA ================================
    //============================================ DLA KLAWIATUR ===================================================
    public static void shoppingCartKeyboardLog()
    {
        for(int j=0; j< keyboardOnListLog.size(); j++)
        {
            sumLog = sumLog + shoppingCartSum(keyboardOnListLog.get(j), "keyboard","keyboard");
            quantityCountKeyboardLog(j);
        }
    }
    public static void quantityCountKeyboardLog(int j)
    {
        if(keyboardOnListLog.get(j)==1)
        {
            quantityKeyboardLog[0]++;
        }
        else if(keyboardOnListLog.get(j)==2)
        {
            quantityKeyboardLog[1]++;
        }
        else if(keyboardOnListLog.get(j)==3)
        {
            quantityKeyboardLog[2]++;
        }
        else if(keyboardOnListLog.get(j)==4)
        {
            quantityKeyboardLog[3]++;
        }
        else
        {
            quantityKeyboardLog[4]++;
        }
    }

    //================================ SEKCJA KOSZYKA, ZAKUPU Z STWORZONEGO KOSZYKA ================================
    //============================================ DLA MYSZEK ===================================================
    public static void shoppingCartMouseLog()
    {
        for(int j=0; j< mouseOnListLog.size(); j++)
        {
            sumLog = sumLog + shoppingCartSum(mouseOnListLog.get(j), "monitor","monitor");
            quantityCountMouseLog(j);
        }
    }

    public static void quantityCountMouseLog(int j)
    {
        if(mouseOnListLog.get(j)==1)
        {
            quantityMouseLog[0]++;
        }
        else if(mouseOnListLog.get(j)==2)
        {
            quantityMouseLog[1]++;
        }
        else if(mouseOnListLog.get(j)==3)
        {
            quantityMouseLog[2]++;
        }
        else if(mouseOnListLog.get(j)==4)
        {
            quantityMouseLog[3]++;
        }
        else
        {
            quantityMouseLog[4]++;
        }
    }

    //================================ SEKCJA KOSZYKA, ZAKUPU Z STWORZONEGO KOSZYKA ================================
    //============================================ PODSUMOWUJĄCA ==================================================
    static int[] quantityPcLog = {0,0,0,0,0};
    static int[] quantityLaptopLog = {0,0,0,0,0};
    static int[] quantityTabletLog = {0,0,0,0,0};
    static int[] quantitySmartphoneLog = {0,0,0,0,0};
    static int[] quantityMonitorLog = {0,0,0,0,0};
    static int[] quantityKeyboardLog = {0,0,0,0,0};
    static int[] quantityMouseLog = {0,0,0,0,0};
    public static void shoppingCartTotalLog()
    {
        //wywołanie funkcji liczących cenę i ilość przedmiotów
        moneyLog = shoppingCartPCLog();
        shoppingCartLaptopLog();
        shoppingCartTabletLog();
        shoppingCartSmartphoneLog();
        shoppingCartMonitorLog();
        shoppingCartKeyboardLog();
        shoppingCartMouseLog();
        if((moneyLog-sumLog)<0 || moneyLog==0)
        {
            System.out.println("Your order has been canceled! :(\n");
            pcOnListLog.clear(); laptopOnListLog.clear(); tabletOnListLog.clear(); smartphoneOnListLog.clear(); monitorOnListLog.clear(); keyboardOnListLog.clear(); mouseOnListLog.clear();
        }
        else
        {
            System.out.println("THE ORDER HAS BEEN SENT :)");
            //pętla do aktualizacji produktów
            for(int j=0; j<5;j++)
            {
                //pobranie akutalnej ilosci komputerow i wprowadzenie do bazy
                int quantityActualPc = shoppingCartQuantityCheck(j+1, "pc","PC");
                int quantityNewPc = shoppingCartQuantityUpdate(j+1,"pc","PC", (quantityActualPc- quantityPcLog[j]));

                //pobieranie akutalnej ilosci laptopow i wprowadzenie do bazy
                int quantityActualLaptop = shoppingCartQuantityCheck(j+1, "laptop","Laptop");
                int quantityNewLaptop = shoppingCartQuantityUpdate(j+1, "laptop", "Laptop", (quantityActualLaptop-quantityLaptopLog[j]));

                //pobieranie akutalnej ilosci tabletow i wprowadzenie do bazy
                int quantityAcutalTablet = shoppingCartQuantityCheck(j+1,"tablet","tablet");
                int quantityNewTablet = shoppingCartQuantityUpdate(j+1, "tablet", "tablet",(quantityAcutalTablet-quantityTabletLog[j]));

                //pobieranie akutalnej ilosci telefonow i wprowadzenie do bazy
                int quantityActualSmartphone = shoppingCartQuantityCheck(j+1, "smartphone","smartphone");
                int quantityNewSmartphone = shoppingCartQuantityUpdate(j+1, "smartphone","smartphone",(quantityActualSmartphone-quantitySmartphoneLog[j]));

                //pobieranie akutalnej ilosci monitorow i wprowadzenie do bazy
                int quantityActualMonitor = shoppingCartQuantityCheck(j+1, "monitor", "monitor");
                int quantityNewMonitor = shoppingCartQuantityUpdate(j+1, "monitor", "monitor", (quantityActualMonitor-quantityMonitorLog[j]));

                //pobieranie akutalnej ilosci klawiatur i wprowadzenie do bazy
                int quantityActualKeyboard = shoppingCartQuantityCheck(j+1, "keyboard", "keyboard");
                int quantityNewKeyboard = shoppingCartQuantityUpdate(j+1, "keyboard", "keyboard", (quantityActualKeyboard-quantityKeyboardLog[j]));

                //pobieranie akutalnej ilosci mysz i wprowadzenie do bazy
                int quantityActualMouse = shoppingCartQuantityCheck(j+1, "mouse", "mouse");
                int quantityNewMouse = shoppingCartQuantityUpdate(j+1, "mouse", "mouse", (quantityActualMouse-quantityMouseLog[j]));
            }
            //czyszczenie listy po zakupach
            pcOnListLog.clear(); laptopOnListLog.clear(); tabletOnListLog.clear(); smartphoneOnListLog.clear(); monitorOnListLog.clear(); keyboardOnListLog.clear(); mouseOnListLog.clear();
        }
    }


    //========================================= SEKCJA DODAWANIA DO KOSZYKA PRODUKTÓW ==================================
    public static void buyingPCLog()
    {
        while (true)
        {
            System.out.print("Add to shopping cart? (yes/no): ");
            String choiceBuyOrNot = inputString();
            if(choiceBuyOrNot.equals("yes"))
            {
                System.out.print("Which option do you want to buy?:");
                int choiceBuy = inputInt();
                switch (choiceBuy)
                {
                    case 1:
                        System.out.println("Computer number 1 has been added to the cart!\n");
                        pcOnListLog.add(1);
                        break;
                    case 2:
                        System.out.println("Computer number 2 has been added to the cart!\n");
                        pcOnListLog.add(2);
                        break;
                    case 3:
                            System.out.println("Computer number 3 has been added to the cart!\n");
                            pcOnListLog.add(3);
                            break;
                    case 4:
                            System.out.println("Computer number 4 has been added to the cart!\n");
                            pcOnListLog.add(4);
                            break;
                    case 5:
                        System.out.println("Computer number 5 has been added to the cart!\n");
                        pcOnListLog.add(5);
                        break;
                    default:
                        System.out.print("Enter correct number!\n");
                    }
            }
            else
            {
                System.out.println();
                break;
            }
        }
    }
    //sekcja do wielokrotnego dodawania do koszyka laptopów
    public static void buyingLaptopLog()
    {
        while (true)
        {
            System.out.print("Add to shopping cart? (yes/no): ");
            String choiceBuyOrNot = inputString();
            if(choiceBuyOrNot.equals("yes"))
            {
                System.out.print("Which option do you want to buy?:");
                int choiceBuy = inputInt();
                switch (choiceBuy)
                {
                    case 1:
                        System.out.println("Laptop number 1 has been added to the cart!\n");
                        laptopOnListLog.add(1);
                        break;
                    case 2:
                        System.out.println("Laptop number 2 has been added to the cart!\n");
                        laptopOnListLog.add(2);
                        break;
                    case 3:
                        System.out.println("Laptop number 3 has been added to the cart!\n");
                        laptopOnListLog.add(3);
                        break;
                    case 4:
                        System.out.println("Laptop number 4 has been added to the cart!\n");
                        laptopOnListLog.add(4);
                        break;
                    case 5:
                        System.out.println("Laptop number 5 has been added to the cart!\n");
                        laptopOnListLog.add(5);
                        break;
                    default:
                        System.out.print("Enter correct number!\n");
                }
            }
            else
            {
                System.out.println();
                break;
            }
        }
    }
    //sekcja do wielokrotnego dodawania do koszyka tabletow
    public static void buyingTabletLog()
    {
        while (true)
        {
            System.out.print("Add to shopping cart? (yes/no): ");
            String choiceBuyOrNot = inputString();
            if(choiceBuyOrNot.equals("yes"))
            {
                System.out.print("Which option do you want to buy?:");
                int choiceBuy = inputInt();
                switch (choiceBuy)
                {
                    case 1:
                        System.out.println("Tablet number 1 has been added to the cart!\n");
                        tabletOnListLog.add(1);
                        break;
                    case 2:
                        System.out.println("Tablet number 2 has been added to the cart!\n");
                        tabletOnListLog.add(2);
                        break;
                    case 3:
                        System.out.println("Tablet number 3 has been added to the cart!\n");
                        tabletOnListLog.add(3);
                        break;
                    case 4:
                        System.out.println("Tablet number 4 has been added to the cart!\n");
                        tabletOnListLog.add(4);
                        break;
                    case 5:
                        System.out.println("Tablet number 5 has been added to the cart!\n");
                        tabletOnListLog.add(5);
                        break;
                    default:
                        System.out.print("Enter correct number!\n");
                }
            }
            else
            {
                System.out.println();
                break;
            }
        }
    }
    //sekcja do wielokrotnego dodawania do koszyka monitorow
    public static void buyingMonitorLog()
    {
        while (true)
        {
            System.out.print("Add to shopping cart? (yes/no): ");
            String choiceBuyOrNot = inputString();
            if(choiceBuyOrNot.equals("yes"))
            {
                System.out.print("Which option do you want to buy?:");
                int choiceBuy = inputInt();
                switch (choiceBuy)
                {
                    case 1:
                        System.out.println("Monitor number 1 has been added to the cart!\n");
                        monitorOnListLog.add(1);
                        break;
                    case 2:
                        System.out.println("Monitor number 2 has been added to the cart!\n");
                        monitorOnListLog.add(2);
                        break;
                    case 3:
                        System.out.println("Monitor number 3 has been added to the cart!\n");
                        monitorOnListLog.add(3);
                        break;
                    case 4:
                        System.out.println("Monitor number 4 has been added to the cart!\n");
                        monitorOnListLog.add(4);
                        break;
                    case 5:
                        System.out.println("Monitor number 5 has been added to the cart!\n");
                        monitorOnListLog.add(5);
                        break;
                    default:
                        System.out.print("Enter correct number!\n");
                }
            }
            else
            {
                System.out.println();
                break;
            }
        }
    }
    //sekcja do wielokrotnego dodawania do koszyka telefonow
    public static void buyingSmartphoneLog()
    {
        while (true)
        {
            System.out.print("Add to shopping cart? (yes/no): ");
            String choiceBuyOrNot = inputString();
            if(choiceBuyOrNot.equals("yes"))
            {
                System.out.print("Which option do you want to buy?:");
                int choiceBuy = inputInt();
                switch (choiceBuy)
                {
                    case 1:
                        System.out.println("Smartphone number 1 has been added to the cart!\n");
                        smartphoneOnListLog.add(1);
                        break;
                    case 2:
                        System.out.println("Smartphone number 2 has been added to the cart!\n");
                        smartphoneOnListLog.add(2);
                        break;
                    case 3:
                        System.out.println("Smartphone number 3 has been added to the cart!\n");
                        smartphoneOnListLog.add(3);
                        break;
                    case 4:
                        System.out.println("Smartphone number 4 has been added to the cart!\n");
                        smartphoneOnListLog.add(4);
                        break;
                    case 5:
                        System.out.println("Smartphone number 5 has been added to the cart!\n");
                        smartphoneOnListLog.add(5);
                        break;
                    default:
                        System.out.print("Enter correct number!\n");
                }
            }
            else
            {
                System.out.println();
                break;
            }
        }
    }

    //sekcja do wielokrotnego dodawania do koszyka klawiatur
    public static void buyingKeyboardLog()
    {
        while (true)
        {
            System.out.print("Add to shopping cart? (yes/no): ");
            String choiceBuyOrNot = inputString();
            if(choiceBuyOrNot.equals("yes"))
            {
                System.out.print("Which option do you want to buy?:");
                int choiceBuy = inputInt();
                switch (choiceBuy)
                {
                    case 1:
                        System.out.println("Keyboard number 1 has been added to the cart!\n");
                        keyboardOnListLog.add(1);
                        break;
                    case 2:
                        System.out.println("Keyboard number 2 has been added to the cart!\n");
                        keyboardOnListLog.add(2);
                        break;
                    case 3:
                        System.out.println("Keyboard number 3 has been added to the cart!\n");
                        keyboardOnListLog.add(3);
                        break;
                    case 4:
                        System.out.println("Keyboard number 4 has been added to the cart!\n");
                        keyboardOnListLog.add(4);
                        break;
                    case 5:
                        System.out.println("Keyboard number 5 has been added to the cart!\n");
                        keyboardOnListLog.add(5);
                        break;
                    default:
                        System.out.print("Enter correct number!\n");
                }
            }
            else
            {
                System.out.println();
                break;
            }
        }
    }
    //sekcja do wielokrotnego dodawania do koszyka myszek
    public static void buyingMouseLog()
    {
        while (true)
        {
            System.out.print("Add to shopping cart? (yes/no): ");
            String choiceBuyOrNot = inputString();
            if(choiceBuyOrNot.equals("yes"))
            {
                System.out.print("Which option do you want to buy?:");
                int choiceBuy = inputInt();
                switch (choiceBuy)
                {
                    case 1:
                        System.out.println("Mouse number 1 has been added to the cart!\n");
                        mouseOnListLog.add(1);
                        break;
                    case 2:
                        System.out.println("Mouse number 2 has been added to the cart!\n");
                        mouseOnListLog.add(2);
                        break;
                    case 3:
                        System.out.println("Mouse number 3 has been added to the cart!\n");
                        mouseOnListLog.add(3);
                        break;
                    case 4:
                        System.out.println("Mouse number 4 has been added to the cart!\n");
                        mouseOnListLog.add(4);
                        break;
                    case 5:
                        System.out.println("Mouse number 5 has been added to the cart!\n");
                        mouseOnListLog.add(5);
                        break;
                    default:
                        System.out.print("Enter correct number!\n");
                }
            }
            else
            {
                System.out.println();
                break;
            }
        }
    }

    //================================ SEKCJA SCANNEROW DO METOD ================================
    public static String inputString()
    {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
    public static int inputInt()
    {
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

    //================================ SEKCJA MENU, PRINTY ================================
    public static void menu()
    {
        System.out.println("****************************************");
        System.out.println("1) Shopping for standard customers     *");
        System.out.println("2) Wholesale purchases                 *");
        System.out.println("3) Admin panel (login required)        *");
        System.out.println("4) Exit from store                     *");
        System.out.println("****************************************");
    }
    public static void menuProducts()
    {
        System.out.println("1) Show Personal Computers");
        System.out.println("2) Show Laptops");
        System.out.println("3) Show Tablet");
        System.out.println("4) Show Smartphones");
        System.out.println("5) Show Monitors");
        System.out.println("6) Show Keyboards");
        System.out.println("7) Show Mouse");
        System.out.println("8) Buy things from your own shopping cart");
        System.out.println("9) GO BACK");
    }
}