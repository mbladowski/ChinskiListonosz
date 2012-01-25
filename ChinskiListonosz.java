/**
 * Problem chi�skiego listonosza / TGiS
 * Autor: Micha� Bladowski, III rok Informatyki
 * Rok: 2012
 */

 import java.awt.*;
 import java.awt.event.*;
 import java.awt.font.*;
 import java.util.*;
 import java.awt.geom.*;
 import javax.swing.*;
 
 public class ChinskiListonosz
 {
    public static void main(String[] args)
    {
       RamkaListonosza ramka = new RamkaListonosza();
       ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       ramka.show();
    }
 }
 
 /**
    Ramka zawierajaca panel calej aplikacji
 */
 class RamkaListonosza extends JFrame
 {
     
    Dzialanie dz = new Dzialanie();
    PanelMyszki panel = new PanelMyszki();
    
    public RamkaListonosza()
    {
       setTitle("Problem chi�skiego listonosza");
       setSize(SZEROKOSC, WYSOKOSC);
 
       // dolacz panel do ramki
       Container powZawartosci = getContentPane();
       
        /* Sekcja menu */
       
        //------------------
        // SEKCJA MENU
        //------------------

        // menu opcji
        JMenu menuOpcje = new JMenu("Opcje");
        
        elemWyznacz = menuOpcje.add(new Wyznacz("Wyznacz drog�"));
//        if(!(panel.kwadraty.isEmpty()))
//        {
        
elemWyznacz.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 
InputEvent.CTRL_DOWN_MASK));
        elemWyznacz.addActionListener(new
          ActionListener()
          {
             public void actionPerformed(ActionEvent zdarzenie)
             {
                panel.RozwiazProblemListonosza();
             }
          });    
//        } 
//        else 
//        {
//            elemWyznacz.setEnabled(false);
//        }
        
        elemWyczysc = menuOpcje.add(new Wyczysc("Wyczy��"));
//        if(!(panel.kwadraty.isEmpty()))
//        {
        elemWyczysc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, 
InputEvent.CTRL_DOWN_MASK));
        elemWyczysc.addActionListener(new
          ActionListener()
          {
             public void actionPerformed(ActionEvent zdarzenie)
             {
                panel.removeThemAll();
             }
          });
//        }
//        else 
//        {
//            elemWyczysc.setEnabled(false);
//        }
        
        menuOpcje.addSeparator();

        elemCofnij = menuOpcje.add(new Cofnij("Cofnij"));
        elemCofnij.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, 
InputEvent.CTRL_DOWN_MASK));
        elemCofnij.setEnabled(false);
        
        elemDalej = menuOpcje.add(new Dalej("Dalej"));
        elemDalej.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, 
InputEvent.CTRL_DOWN_MASK));
        elemDalej.setEnabled(false);       
        
        menuOpcje.addSeparator();

        menuOpcje.add(new
                AbstractAction("Zamknij") {
                    public void actionPerformed(ActionEvent zdarzenie){
                        System.exit(0);
                    }
                });

        // menu pomoc
        JMenu menuPomoc = new JMenu("Pomoc");
        
        elemWskazowki = menuPomoc.add(new JMenuItem("Wskaz�wki"));
        elemWskazowki.addActionListener(new
          ActionListener()
          {
             public void actionPerformed(ActionEvent zdarzenie)
             {
                if (okno_wsk == null) // pierwszy raz
                   okno_wsk = new OknoInformacji(RamkaListonosza.this, 
"Wskaz�wki", dz.get_wskazowki(), 620, 380);
                okno_wsk.show(); // pokaz okno
             }
          });

        elemAutor = menuPomoc.add(new JMenuItem("O autorze"));
        elemAutor.addActionListener(new
          ActionListener()
          {
             public void actionPerformed(ActionEvent zdarzenie)
             {
                if (okno_aut == null) // pierwszy raz
                   okno_aut = new OknoInformacji(RamkaListonosza.this, 
"O autorze", dz.get_autor(), 235, 180);
                okno_aut.show(); // pokaz okno
             }
          });

        // glowny pasek menu
        JMenuBar pasekMenu = new JMenuBar();
        setJMenuBar(pasekMenu);

        pasekMenu.add(menuOpcje);
        pasekMenu.add(menuPomoc);
        
        /* koniec sekcji menu */
       
       powZawartosci.add(panel);
    }
 
    /* Sekcja pol skladowych */
    
    // POLA SKLADOWE
    public static final int SZEROKOSC = 650;
    public static final int WYSOKOSC = 450;
    private JMenuItem elemWyczysc;
    private JMenuItem elemCofnij;
    private JMenuItem elemDalej;
    private JMenuItem elemWyznacz;
    private JMenuItem elemWskazowki;
    private JMenuItem elemAutor;
    private OknoInformacji okno_wsk;
    private OknoInformacji okno_aut;
        
    /* Koniec sekcji pol skladowych */
 }
 
 /**
  * Panel, do kt rego za pomoc  myszki mo na dodawa  i usuwa  kwadraty
  */
 class PanelMyszki extends JPanel
 {
    public PanelMyszki()
    {
       SYMBOLE = new ArrayList();
       SYMBOLE.add(new String("A"));
       SYMBOLE.add(new String("B"));
       SYMBOLE.add(new String("C"));
       SYMBOLE.add(new String("D"));
       SYMBOLE.add(new String("E"));
       SYMBOLE.add(new String("F"));
       SYMBOLE.add(new String("G"));
       SYMBOLE.add(new String("H"));
       SYMBOLE.add(new String("I"));
       SYMBOLE.add(new String("J"));
       SYMBOLE.add(new String("K"));
       SYMBOLE.add(new String("L"));
       SYMBOLE.add(new String("M"));
       SYMBOLE.add(new String("N"));
       SYMBOLE.add(new String("O"));
       SYMBOLE.add(new String("P"));
       SYMBOLE.add(new String("Q"));
       SYMBOLE.add(new String("R"));
       SYMBOLE.add(new String("S"));
       SYMBOLE.add(new String("T"));
       SYMBOLE.add(new String("U"));
       SYMBOLE.add(new String("V"));
       SYMBOLE.add(new String("W"));
       SYMBOLE.add(new String("X"));
       SYMBOLE.add(new String("Y"));
       SYMBOLE.add(new String("Z"));
       kwadraty = new ArrayList();
       krawedzie = new ArrayList();
       obecny = null;
       obecna = null;
 
       addMouseListener(new UchwytMyszki());
       addMouseMotionListener(new UchwytRuchuMyszki());
    }
 
    public void paintComponent(Graphics g)
    {
       super.paintComponent(g);
       Graphics2D g2 = (Graphics2D)g;
       Font f = new Font("Serif", Font.BOLD, 12);
       FontRenderContext kontekst = g2.getFontRenderContext();
       String s;
       
       // narysuj wszystkie kwadraty (wezly)
       for (int i = 0; i < kwadraty.size(); i++) 
       {
           s = SYMBOLE.get(i).toString();
           Rectangle2D gr = f.getStringBounds(s, kontekst);
           g2.drawString(s, 
(int)((Wezel)(kwadraty.get(i))).get_kwadrat().getX()+DLUGOSCBOKU/3, 
(int)((Wezel)(kwadraty.get(i))).get_kwadrat().getY()+DLUGOSCBOKU*3/4);
           g2.setPaint(Color.BLACK);
           
g2.draw((Rectangle2D)((Wezel)(kwadraty.get(i))).get_kwadrat());
       }
       // narysuj wszystkie krawedzie
       for (int i = 0; i < krawedzie.size(); i++)
       {
           double x = 
((((Krawedz)krawedzie.get(i)).get_krawedz()).getX1()+(((Krawedz)krawedzie.get(i)).get_krawedz()).getX2())/2;
           double y = 
((((Krawedz)krawedzie.get(i)).get_krawedz()).getY1()+(((Krawedz)krawedzie.get(i)).get_krawedz()).getY2())/2;
           s = Integer.toString(((Krawedz)krawedzie.get(i)).get_waga());
           g2.drawString(s, (float)x+DLUGOSCBOKU/2, 
(float)y+DLUGOSCBOKU/2);
           g2.draw((Line2D)((Krawedz)krawedzie.get(i)).get_krawedz());
       }
    }
 
    /**
       Znajdz pierwszy kwadrat, zawierajacy dany punkt
       @param p punkt
       @return indeks pierwszego kwadratu, zawierajacego p
    */
    public Rectangle2D find(Point2D p)
    {
       for (int i = 0; i < kwadraty.size(); i++)
       {
          Rectangle2D r = 
(Rectangle2D)((Wezel)kwadraty.get(i)).get_kwadrat();
          if (r.contains(p)) return r;
       }
 
       return null;
    }
    
    /**
       Znajdz wezel, zawierajacy dany kwadrat
       @param p punkt
       @return indeks pierwszego kwadratu, zawierajacego p
    */
    public Wezel find(Rectangle2D r)
    {
       for (int i = 0; i < kwadraty.size(); i++)
       {
          Wezel w = (Wezel)kwadraty.get(i);
          if (w.get_kwadrat().equals(r)) return w;
       }
 
       return null;
    }   
 
    /**
       Dolacza nowy kwadrat do zbioru
       @param p  srodek kwadratu
    */
    public void add(Point2D p)
    {
       Wezel wezel = new Wezel(p, kwadraty.size());
        
       obecny = wezel.get_kwadrat();
       kwadraty.add(wezel);
       repaint();
    }

    /**
       Usuwa kwadrat ze zbioru
       @param s kwadrat, ktory ma zostac usuniety
    */
    public void remove(Wezel s)
    {
       if (s == null) return;
       if (s.get_kwadrat() == obecny) obecny = null;
       for(int i = 0; i < krawedzie.size(); i++)
       {
           Krawedz kr = (Krawedz)(krawedzie.get(i));
           if(kr.get_wezel1() == s.get_id()) remove(kr);
           if(kr.get_wezel2() == s.get_id()) remove(kr);
       }
       kwadraty.remove(s);
       repaint();
    }
    
    /**
       Usuwa wszystkie kwadraty i krawedzie ze zbiorow
    */
    public void removeThemAll()
    {
       obecny = null;
       obecna = null;
       kwadraty.removeAll(kwadraty);
       krawedzie.removeAll(krawedzie);
       repaint();
    }  
    
    /**
     * Rysowanie krawedzi
     */
    public void add(Point2D p1, Point2D p2, int waga)
    {  
       obecna = new Line2D.Double(p1, p2);
       Krawedz kr = new Krawedz(obecna, waga);
       krawedzie.add(kr);
       repaint();
    }    

    /**
       Usuwa krawedz ze zbioru
       @param s krawedz, ktora ma zostac usunieta
    */
    public void remove(Krawedz s)
    {
       if (s == null) return;
       if (s.get_krawedz() == obecna) obecna = null;
       krawedzie.remove(s);
       repaint();
    }
 
    private static final int DLUGOSCBOKU = 18;
    private static ArrayList SYMBOLE;
    public ArrayList kwadraty;
    private ArrayList krawedzie;
    private Rectangle2D obecny; // kwadrat, w ktorym znajduje sie kursor 
myszki
    private Line2D obecna; // krawedz, na ktorej znajduje sie kursor 
myszki
 
    private class UchwytMyszki extends MouseAdapter
    {
       private OknoDodawaniaKrawedzi okno;
       
       public void mousePressed(MouseEvent zdarzenie)
       {
          // jesli kursor nie znajduje sie wewnatrz kwadratu, narysuj 
nowy kwadrat
          obecny = find(zdarzenie.getPoint());
          if (obecny == null && zdarzenie.getClickCount() >= 2)
             add(zdarzenie.getPoint());
       }
 
       public void mouseClicked(MouseEvent zdarzenie)
       {
           JPopupMenu menu = new JPopupMenu();
          // jesli nastapilo klikniecie prawym przyciskiem myszy, 
wyswietl menu
          obecny = find(zdarzenie.getPoint());
              
          if (obecny != null && ((zdarzenie.getModifiers() & 
InputEvent.BUTTON3_MASK) != 0 ))
          {
             menu.add(new
                AbstractAction("Usu�") {
                    public void actionPerformed(ActionEvent zdarzenie){
                        remove(find(obecny));
                    }
                });
             menu.add(new
                AbstractAction("Dodaj kraw�d� do: ") {
                    public void actionPerformed(ActionEvent zdarzenie){
                            okno = new 
OknoDodawaniaKrawedzi(PanelMyszki.this, 400, 300);
                            okno.show(); // pokaz okno
                }});
             menu.show(zdarzenie.getComponent(), zdarzenie.getX(), 
zdarzenie.getY());
                }
                     
       }
       
    }
 
    private class UchwytRuchuMyszki implements MouseMotionListener
    {
       public void mouseMoved(MouseEvent zdarzenie)
       {
          // jesli kursor myszki znajduje sis wewnatrz kwadratu, 
          // zmien go w symbol przesuniecia
 
          if (find(zdarzenie.getPoint()) == null)
             setCursor(Cursor.getDefaultCursor());
          else
             setCursor(Cursor.getPredefinedCursor
                (Cursor.MOVE_CURSOR));
       }
 
       public void mouseDragged(MouseEvent zdarzenie) // przesuwanie 
krawedzi do poprawki !
       {
          if (obecny != null)
          {
             int x = zdarzenie.getX();
             int y = zdarzenie.getY();
             Wezel w = null;
 
             // przesun obecny kwadrat tak, aby jego srodek znajdowal 
sie w (x, y)
             obecny.setFrame(
                x - DLUGOSCBOKU / 2,
                y - DLUGOSCBOKU / 2,
                DLUGOSCBOKU,
                DLUGOSCBOKU);
             
             for(int i=0; i < kwadraty.size(); i++)
             {
                
if(((Rectangle2D)((Wezel)(kwadraty.get(i))).get_kwadrat()).equals(obecny)) 
w = (Wezel)(kwadraty.get(i));
             }
             for(int i = 0; i < krawedzie.size(); i++)
             {
                   Krawedz kr = (Krawedz)(krawedzie.get(i));
                   if(kr.get_wezel1() == w.get_id()) 
                   {
                       obecna.setLine(new Point2D.Double(x,y), 
obecna.getP2());
                       kr.set(obecna, kr.get_waga());
                   }
                   else if(kr.get_wezel2() == w.get_id())
                   {
                       obecna.setLine(obecna.getP1(), new 
Point2D.Double(x,y));
                       kr.set(obecna, kr.get_waga());
                   }
             }
             repaint();
          }
       }
    }
    
 /**
  * Klasa OknoKrawedzi
  * U�ywana jest do wy�wietlania wskaz�wek
  * oraz informacji o autorze
  */
 class OknoDodawaniaKrawedzi extends JDialog
 {
    private ButtonGroup grupa_wezlow = new ButtonGroup();
    private JTextField poleWaga;
    public OknoDodawaniaKrawedzi(JPanel wlasciciel, int width, int 
height)
    {
       //super(wlasciciel, "Menu dodawania kraw�dzi", true);
       setLayout(new GridLayout(2,2));
       
       Container cp = getContentPane();
       
       JPanel mainy = new JPanel();
       mainy.setLayout(new GridLayout(2,2));
       mainy.add(new Label("W�ze� docelowy:"));
                        for(int i=0; i < kwadraty.size(); i++)
                        {
                            
if(!((Rectangle2D)((Wezel)(kwadraty.get(i))).get_kwadrat()).equals(obecny))
                            {
                                JRadioButton b = new 
JRadioButton(SYMBOLE.get(i).toString());
                                
b.setActionCommand(SYMBOLE.get(i).toString());
                                mainy.add(b);
                                grupa_wezlow.add(b);           
                            }
                        }
                        mainy.add(new Label("Waga kraw�dzi:"));
                        mainy.add(poleWaga = new JTextField("",10));

                        JButton przyciskOK  = new JButton("Ok");
                        przyciskOK.addActionListener(new 
                        ActionListener()
                        {
                            public void actionPerformed(ActionEvent z)
                            {
                                setVisible(false);
                                String pobrany_wezel = pobierzWezel();
                                int pobrana_waga = pobierzWage();
                                Point2D p1 = new 
Point2D.Double(obecny.getCenterX(),obecny.getCenterY());
                                Point2D p2 = new 
Point2D.Double(((Wezel)(kwadraty.get(SYMBOLE.indexOf(pobrany_wezel)))).get_x(),((Wezel)(kwadraty.get(SYMBOLE.indexOf(pobrany_wezel)))).get_y());
                                add(p1,p2,pobrana_waga);
                            }
                        });
                        
                        JButton przyciskAnuluj  = new JButton("Anuluj");
                        przyciskAnuluj.addActionListener(new 
                        ActionListener()
                        {
                            public void actionPerformed(ActionEvent z)
                            {
                                setVisible(false);
                            }
                        });

       // do��czam przycisk 'Ok' do po�udniowej kraw�dzi
       JPanel dol = new JPanel();
       dol.add(przyciskOK);
       dol.add(przyciskAnuluj);
       cp.add(mainy, BorderLayout.NORTH);
       cp.add(dol, BorderLayout.SOUTH);
       setTitle("Menu dodawania kraw�dzi");
       setSize(width, height);
    }
    
    public String pobierzWezel()
    {
        String s = 
grupa_wezlow.getSelection().getActionCommand().toString();
        return s;
    }
    
    public int pobierzWage()
    {
        int t = 1;
        String s = poleWaga.getText();
        if(!s.isEmpty()) t = Integer.parseInt(s);
        return t;
    }
}  
 
/**
 * Wezel
 */
class Wezel {
    
    private int wezel_id;
    private Point2D wezel;
    private Rectangle2D kwadrat;
    
    Wezel(Point2D w, int id)
    {
        set(w, id);
    }
    
    private void set(Point2D w, int id)
    {
        wezel_id = id;
        wezel = w;
        kwadrat = new Rectangle2D.Double(
          w.getX() - DLUGOSCBOKU / 2,
          w.getY() - DLUGOSCBOKU / 2,
          DLUGOSCBOKU,
          DLUGOSCBOKU);
    }
    
    public double get_x()
    {
        return wezel.getX();
    }
    
    public double get_y()
    {
        return wezel.getY();
    }
    
    public int get_id()
    {
        return wezel_id;
    }  
    
    public Rectangle2D get_kwadrat()
    {
        return kwadrat;
    }
    
}
 
/**
 * Krawedz
 */
class Krawedz {
    
    private Line2D krawedz;
    private int waga;

    Krawedz(Line2D linia, int w)
    {
        set(linia, w);
    }
    
    private void set(Line2D linia, int w)
    {
        krawedz = linia;
        waga = w;
    }
    
    public Line2D get_krawedz()
    {
        return krawedz;
    }
    
    public int get_wezel1()
    {
        int r = -1;
        //return kwadraty.indexOf(find(krawedz.getP1()));
        for(int i = 0; i < kwadraty.size(); i++)
        {
            Wezel x = (Wezel)(kwadraty.get(i));
            if (x.kwadrat.equals(find(krawedz.getP1()))) r = x.get_id();
        }
        return r;
    }
    
    public int get_wezel2()
    {
        int r = -1;
        //return kwadraty.indexOf(find(krawedz.getP2()));
        for(int i = 0; i < kwadraty.size(); i++)
        {
            Wezel x = (Wezel)(kwadraty.get(i));
            if (x.kwadrat.equals(find(krawedz.getP2()))) r = x.get_id();
        }
        return r;
    }
    
    public int get_waga()
    {
        return waga;
    }    
    
}

/**
 * ALGORYTMY
 */
    ArrayList[] L;
    ArrayList Q = new ArrayList();
    boolean[] visited;

    // Glowna metoda !!!
    public void RozwiazProblemListonosza() {

        L = new ArrayList[kwadraty.size()];
        visited = new boolean[kwadraty.size()];
        
        for(int i = 0; i < kwadraty.size(); i++) 
        {
            L[i] = new ArrayList();
            for(int j = 0; j < krawedzie.size(); j++)
            {
                if((((Wezel)(kwadraty.get(i))).wezel_id) == 
((Krawedz)krawedzie.get(j)).get_wezel1()) 
                {
                    L[i].add(((Krawedz)krawedzie.get(j)).get_wezel2());
                }
                else if((((Wezel)(kwadraty.get(i))).wezel_id) == 
((Krawedz)krawedzie.get(j)).get_wezel2())
                {
                    L[i].add(((Krawedz)krawedzie.get(j)).get_wezel1());
                }
            }
        }
        boolean spojny = czy_spojny();
        if(spojny)
        {
            sprawdz_stopnie_wierzcholkow();
        } 
        else 
        {
            System.out.println("Graf nie jest sp�jny, rozwi�zanie nie 
istnieje !");
        }
    }
    
    // Sprawdzamy spojnosc grafu
    private boolean czy_spojny()
    {
      int c = 0;
      boolean test = true;

      for(int i = 0; i < kwadraty.size(); i++) visited[i] = false;
      for(int i = 0; i < kwadraty.size(); i++)
      {
        if(!visited[i])
        {
          c++; DFS(i);
        }
      }

      if(c != 1) test = false;    
  
      return test;
    }
    
    // Sprawdzamy parzystosc stopni wierzcholkow
    private void sprawdz_stopnie_wierzcholkow()
    {
        int[] stopnie = new int[kwadraty.size()];
        ArrayList nieparzyste = new ArrayList();
        for(int i = 0; i < kwadraty.size(); i++) { 
            for(int j = 0; j < krawedzie.size(); j++)
            {
                if((((Wezel)(kwadraty.get(i))).wezel_id) == 
((Krawedz)krawedzie.get(j)).get_wezel1()) stopnie[i]++;
                else if((((Wezel)(kwadraty.get(i))).wezel_id) == 
((Krawedz)krawedzie.get(j)).get_wezel2()) stopnie[i]++;
            }
            if(stopnie[i]%2!=0) nieparzyste.add(new Integer(i));
        }
        
        if(!nieparzyste.isEmpty()) 
        {
            //Dijkstra(nieparzyste); // implement
            //Euler();
            System.out.println("Program na razie obs�uguje tylko grafy o 
parzystych stopniach wierzcho�k�w ! :("); //temp
        } 
        else
        {
            Euler();
            System.out.println("Cykl Eulera dla tego grafu to: ");
            for(int i = 0; i < Q.size(); i++)
            {
                if(i == Q.size()-1) 
System.out.print(((String)(SYMBOLE.get((Integer)Q.get(i))))+"\n");
                else 
System.out.print(((String)(SYMBOLE.get((Integer)Q.get(i))))+"->");
            }
        }
    }
    
    // Algorytm Dijkstry
    private void Dijkstra(ArrayList nieparzyste)
    {
        // todo
        //K01: 	S . .;   Q . V
        //K02: 	Dla i = 1,2,...,n :  p(i) . 0;   d(i) . .
        //K03: 	d(vo) . 0
        //K04: 	Dop�ki Q . . wykonuj kroki od K05 do K07
        //K05: 	    u . wierzcho�ek ze zbioru Q o najmniejszym d(u)
        //K06: 	    Q . Q \ u;  S . S . u
        //K07: 	    Dla ka�dego v � Q, takiego �e kraw�d� (u,v) � E, 
je�li d(v) > d(u) + waga(u,v), to  d(v) . d(u) + waga(u,v);   p(v) .  u
        //K08: 	Zako�cz algorytm
        //
        //dla k (wierzcho�ki nieparzystych stopni) > 20 podej�cie 
kombinatoryczne nie ma sensu, 
        //w�wczas trzeba zastosowa� algorytm Edmondsa, o z�o�ono�ci 
O(n^3), ale ten z kolei jest skomplikowany
    }    
    
    // Algorytm Eulera
    private void Euler()
    {
        Q.removeAll(Q);
        DFSEuler(0);
    } 
    
    // Algorytm DFSEuler
    private void DFSEuler(int v)
    {        
        while(!L[v].isEmpty())
        {
            int x = (Integer)(L[v].get(0));
            L[v].remove(L[v].indexOf(x));
            L[x].remove(L[x].indexOf(v));
            DFSEuler(x);
        }
        Q.add(0,v);
    }
    
    // Algorytm DFS
    private void DFS(int v)
    {
        visited[v] = true;
        for(int i = 0; i < L[v].size(); i++)
        {
            if(!visited[(Integer)L[v].get(i)]) 
DFS((Integer)L[v].get(i));
        }
    }

}

//--------------------------------------------------------------------------

/**
 * Dzia�anie - klada przechowuje podstawow� funkcjonalno��
 * programu, np. cofanie, sprawdzanie danych, wskaz�wki, notatk�
 * o autorze.
 */
class Dzialanie {

    Dzialanie() {
    }

    // wskazowki i informacje o autorze
    public String wskazowki = "";
    public String autor = "";

    // historia operacji
    ///// usuniete /////

    //------------------------------------------
    // Funkcja cofaj�ca ostatni� akcj�
    // w zale�no�ci od aktywnego pola
    //------------------------------------------
    public void cofnij(int pole) {
            // todo
    }

    //------------------------------------------
    // Funkcja zwracaj�ca tre�� wskaz�wek
    //------------------------------------------
    public String get_wskazowki(){
        wskazowki = "<html><h2 align='center'>Problem chi�skiego 
listonosza</h2><hr>"
                + "<p align='center'>W teorii graf�w, jest to zadanie 
znalezienia drogi zamkni�tej<br>"
                + "(wracaj�cej do wierzcho�ka pocz�tkowego), 
zawieraj�cej ka�d� kraw�d�<br>"
                + "grafu co najmniej raz i maj�cej minimalny koszt (sum� 
wag kraw�dzi).<br><br>"
                + "Problem ten zosta� sformu�owany po raz pierwszy w 
j�zyku teorii graf�w<br>"
                + "przez chinskiego matematyka Mei Ku Kwana w 1962 
roku.<br></p>"
                + "<h2 align='center'>Opcje</h2><hr>"
                + "<p align='center'>Aby zacz�� od nowa, kliknij na 
pasku narz�dzi 'Opcje' a nast�pnie 'Wyczy��'.<br><br>"
                + "Aby cofn�� operacj�, kliknij na pasku narz�dzi 
'Opcje' a nast�pnie 'Cofnij'.<br><br>"
                + "Aby wykona� ponownie cofni�t� akcj�, kliknij na pasku 
narz�dzi 'Opcje' a nast�pnie 'Dalej'.<br><br>"
                + "Aby rozwi�za� zadany problem, kliknij na pasku 
narz�dzi 'Opcje' a nast�pnie 'Wyznacz drog� listonosza'.</p></html>";
        return wskazowki;
    }

    //------------------------------------------
    // Funkcja zwracaj�ca informacje o autorze
    //------------------------------------------
    public String get_autor(){
        autor = "<html><h3 align='center'><i><u>Autor:</u></i></h3>"
              + "<h2>Micha� Bladowski &copy; 2012</h2></html>";
        return autor;
    }
}

 /**
  * Klasa OknoInformacji
  * U�ywana jest do wy�wietlania wskaz�wek
  * oraz informacji o autorze
  */
 class OknoInformacji extends JDialog
 {
    public OknoInformacji(JFrame wlasciciel, String tytul, String info, 
int width, int height)
    {
       super(wlasciciel, tytul, true);
       Container cp = getContentPane();

       // po�rodku umieszczam etykiet� HTML
       cp.add(new JLabel(info),
          BorderLayout.CENTER);

       // Przycisk 'Ok' zamyka okno
       JButton ok = new JButton("Zamknij");
       ok.addActionListener(new
          ActionListener()
          {
             public void actionPerformed(ActionEvent zd)
             {
                setVisible(false);
             }
          });

       // do��czam przycisk 'Ok' do po�udniowej kraw�dzi
       JPanel panel = new JPanel();
       panel.add(ok);
       cp.add(panel, BorderLayout.SOUTH);

       setSize(width, height);
    }
}

    /**
     * Klasa obs�uguj�ca przycisk Opcje -> Wyczy��
     */
    class Wyczysc extends AbstractAction {

    public Wyczysc(String nazwa) {
        super(nazwa);
    }
    
    public void actionPerformed(ActionEvent zdarzenie) {
        // todo
    }
    }    

    /**
     * Klasa obs�uguj�ca przycisk Opcje -> Cofnij
     */
    class Cofnij extends AbstractAction {

    public Cofnij(String nazwa) {
        super(nazwa);
    }

    public void actionPerformed(ActionEvent zdarzenie) {
        // todo
    }
    }
    
    /**
     * Klasa obs�uguj�ca przycisk Opcje -> Dalej
     */
    class Dalej extends AbstractAction {

    public Dalej(String nazwa) {
        super(nazwa);
    }
    
    public void actionPerformed(ActionEvent zdarzenie) {
        // todo
    }
    }

    /**
     * Klasa obs�uguj�ca przycisk Opcje -> Wyznacz droge
     */
    class Wyznacz extends AbstractAction {

    public Wyznacz(String nazwa) {
        super(nazwa);
    }

    public void actionPerformed(ActionEvent zdarzenie) {
        // todo
    }
    }
