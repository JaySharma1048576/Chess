/**
 * 2-Player Chess
 * Author - Jay Sharma
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Chess extends JFrame implements MouseListener
{
    int square= 100;
    static int piece[][] = new int[40][2];
    static int board[][] = new int[8][8];
    int tempp[][]=new int[40][2];
    int tempb[][]=new int[8][8];
    int tempbpq, tempwpq;
    boolean selected;
    int bpq=0;
    int wpq=0;
    int focus;
    int moves[][] = new int[37][2];
    int castmoves[][] = new int[2][2];
    int c=0, k=0, noofmoves;
    boolean bcheck, wcheck, wmate, bmate, blcastle, wlcastle, bscastle, wscastle, wstale, bstale, draw;
    int turn = 1;
    boolean preview;
    Chess()
    {
        super("2-player Chess game");
        setSize(100+square*8,130+square*8);
        setLayout(null);
        setVisible(true);
        reset();
        addMouseListener(this);
    }

    void reset()
    {
        for(int i=0;i<40;i++)
        {
            if(i<8)
            {
                piece[i][0]=i;
                piece[i][1]=0;
            }
            else if(i<16)
            {
                piece[i][0]=i-8;
                piece[i][1]=1;
            }
            else if(i<24)
            {
                piece[i][0]=i-16;
                piece[i][1]=6;
            }
            else if(i<32)
            {
                piece[i][0]=i-24;
                piece[i][1]=7;
            }
            else
            {
                piece[i][0]=-1;
                piece[i][1]=-1;
            }
        }
        for(int i=2;i<6;i++)
        {
            for(int j=0;j<8;j++)
            {
                board[j][i]=-1;
            }
        }
        for(int i=0;i<8;i++)
            board[i][0]=i;
        for(int i=8;i<16;i++)
            board[i-8][1]=i;
        for(int i=16;i<24;i++)
            board[i-16][6]=i;
        for(int i=24;i<32;i++)
            board[i-24][7]=i;
        bpq=0;
        wpq=0;
        c=0;
        k=0;
        bcheck = false;
        wcheck = false;
        wmate = false;
        bmate = false;
        bstale = false;
        wstale = false;
        draw = false;
        blcastle = true;
        wlcastle = true;
        bscastle = true;
        wscastle = true;
        preview = false;
        turn = 1;
    }

    public static void main(String args[])
    {
        Chess h = new Chess();
        h.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){System.exit(0);}});
    }

    public void paint(Graphics g)
    {
        g.translate(0,30);
        Image br1 = new ImageIcon("Chess Images/white/brook.png").getImage();
        Image bkn2 = new ImageIcon("Chess Images/green/bknight.png").getImage();
        Image bb1 = new ImageIcon("Chess Images/white/bbishop.png").getImage();
        Image bk2 = new ImageIcon("Chess Images/green/bking.png").getImage();
        Image bk1 = new ImageIcon("Chess Images/white/bking.png").getImage();
        Image bk4 = new ImageIcon("Chess Images/red/bking.png").getImage();
        Image bk5 = new ImageIcon("Chess Images/orange/bking.png").getImage();
        Image bk3 = new ImageIcon("Chess Images/yellow/bking.png").getImage();
        Image bq1 = new ImageIcon("Chess Images/white/bqueen.png").getImage();
        Image bq2 = new ImageIcon("Chess Images/green/bqueen.png").getImage();
        Image bb2 = new ImageIcon("Chess Images/green/bbishop.png").getImage();
        Image bkn1 = new ImageIcon("Chess Images/white/bknight.png").getImage();
        Image br2 = new ImageIcon("Chess Images/green/brook.png").getImage();
        Image bp1 = new ImageIcon("Chess Images/white/bpawn.png").getImage();
        Image bp2 = new ImageIcon("Chess Images/green/bpawn.png").getImage();
        Image wr2 = new ImageIcon("Chess Images/green/wrook.png").getImage();
        Image wkn1 = new ImageIcon("Chess Images/white/wknight.png").getImage();
        Image wb2 = new ImageIcon("Chess Images/green/wbishop.png").getImage();
        Image wq1 = new ImageIcon("Chess Images/white/wqueen.png").getImage();
        Image wq2 = new ImageIcon("Chess Images/green/wqueen.png").getImage();
        Image wk2 = new ImageIcon("Chess Images/green/wking.png").getImage();
        Image wk1 = new ImageIcon("Chess Images/white/wking.png").getImage();
        Image wk3 = new ImageIcon("Chess Images/yellow/wking.png").getImage();
        Image wk4 = new ImageIcon("Chess Images/red/wking.png").getImage();        
        Image wk5 = new ImageIcon("Chess Images/orange/wking.png").getImage();
        Image wb1 = new ImageIcon("Chess Images/white/wbishop.png").getImage();
        Image wkn2 = new ImageIcon("Chess Images/green/wknight.png").getImage();
        Image wr1 = new ImageIcon("Chess Images/white/wrook.png").getImage();
        Image wp1 = new ImageIcon("Chess Images/white/wpawn.png").getImage();
        Image wp2 = new ImageIcon("Chess Images/green/wpawn.png").getImage();
        g.setColor(Color.white);
        g.fillRect(0,0,100+square*8,130+square*8);
        g.setColor(new Color(0,200,0));
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                if((i+j)%2==1)
                {
                    g.fillRect(50+j*square,50+i*square,square,square);
                }
            }
        }        
        int brlx=piece[0][0];
        int brly=piece[0][1];
        int bknlx=piece[1][0];
        int bknly=piece[1][1];
        int bblx=piece[2][0];
        int bbly=piece[2][1];
        int brrx=piece[7][0];
        int brry=piece[7][1];
        int bknrx=piece[6][0];
        int bknry=piece[6][1];
        int bbrx=piece[5][0];
        int bbry=piece[5][1];
        int bkx=piece[4][0];
        int bky=piece[4][1];
        int bqx=piece[3][0];
        int bqy=piece[3][1];
        int beq1x=piece[32][0];
        int beq1y=piece[32][1];
        int beq2x=piece[33][0];
        int beq2y=piece[33][1];
        int beq3x=piece[34][0];
        int beq3y=piece[34][1];
        int beq4x=piece[35][0];
        int beq4y=piece[35][1];
        int weq1x=piece[36][0];
        int weq1y=piece[36][1];
        int weq2x=piece[37][0];
        int weq2y=piece[37][1];
        int weq3x=piece[38][0];
        int weq3y=piece[38][1];
        int weq4x=piece[39][0];
        int weq4y=piece[39][1];
        int wrlx=piece[24][0];
        int wrly=piece[24][1];
        int wknlx=piece[25][0];
        int wknly=piece[25][1];
        int wblx=piece[26][0];
        int wbly=piece[26][1];
        int wrrx=piece[31][0];
        int wrry=piece[31][1];
        int wknrx=piece[30][0];
        int wknry=piece[30][1];
        int wbrx=piece[29][0];
        int wbry=piece[29][1];
        int wkx=piece[28][0];
        int wky=piece[28][1];
        int wqx=piece[27][0];
        int wqy=piece[27][1];
        if((brlx!=-1)&&(brly!=-1))
        {
            if((brlx+brly)%2==0)
                g.drawImage(br1,square*brlx+65,square*brly+60,square-30,square-20,this);
            else
                g.drawImage(br2,square*brlx+65,square*brly+60,square-30,square-20,this);
        }
        if((brrx!=-1)&&(brry!=-1))
        {
            if((brrx+brry)%2==0)
                g.drawImage(br1,square*brrx+65,square*brry+60,square-30,square-20,this);
            else
                g.drawImage(br2,square*brrx+65,square*brry+60,square-30,square-20,this);
        }
        if((bknlx!=-1)&&(bknly!=-1))
        {
            if((bknlx+bknly)%2==0)
                g.drawImage(bkn1,square*bknlx+60,square*bknly+60,square-20,square-20,this);
            else
                g.drawImage(bkn2,square*bknlx+60,square*bknly+60,square-20,square-20,this);
        }
        if((bknrx!=-1)&&(bknry!=-1))
        {
            if((bknrx+bknry)%2==0)
                g.drawImage(bkn1,square*bknrx+60,square*bknry+60,square-20,square-20,this);
            else
                g.drawImage(bkn2,square*bknrx+60,square*bknry+60,square-20,square-20,this);
        }
        if((bblx!=-1)&&(bbly!=-1))
        {
            if((bblx+bbly)%2==0)
                g.drawImage(bb1,square*bblx+60,square*bbly+60,square-20,square-20,this);
            else
                g.drawImage(bb2,square*bblx+60,square*bbly+60,square-20,square-20,this);
        }
        if((bbrx!=-1)&&(bbry!=-1))
        {
            if((bbrx+bbry)%2==0)
                g.drawImage(bb1,square*bbrx+60,square*bbry+60,square-20,square-20,this);
            else
                g.drawImage(bb2,square*bbrx+60,square*bbry+60,square-20,square-20,this);
        }
        if((wrlx!=-1)&&(wrly!=-1))
        {
            if((wrlx+wrly)%2==0)
                g.drawImage(wr1,square*wrlx+65,square*wrly+60,square-30,square-20,this);
            else
                g.drawImage(wr2,square*wrlx+65,square*wrly+60,square-30,square-20,this);
        }
        if((wrrx!=-1)&&(wrry!=-1))
        {
            if((wrrx+wrry)%2==0)
                g.drawImage(wr1,square*wrrx+65,square*wrry+60,square-30,square-20,this);
            else
                g.drawImage(wr2,square*wrrx+65,square*wrry+60,square-30,square-20,this);
        }
        if((wknlx!=-1)&&(wknly!=-1))
        {
            if((wknlx+wknly)%2==0)
                g.drawImage(wkn1,square*wknlx+60,square*wknly+60,square-20,square-20,this);
            else
                g.drawImage(wkn2,square*wknlx+60,square*wknly+60,square-20,square-20,this);
        }
        if((wknrx!=-1)&&(wknry!=-1))
        {
            if((wknrx+wknry)%2==0)
                g.drawImage(wkn1,square*wknrx+60,square*wknry+60,square-20,square-20,this);
            else
                g.drawImage(wkn2,square*wknrx+60,square*wknry+60,square-20,square-20,this);
        }
        if((wblx!=-1)&&(wbly!=-1))
        {
            if((wblx+wbly)%2==0)
                g.drawImage(wb1,square*wblx+60,square*wbly+60,square-20,square-20,this);
            else
                g.drawImage(wb2,square*wblx+60,square*wbly+60,square-20,square-20,this);
        }
        if((wbrx!=-1)&&(wbry!=-1))
        {
            if((wbrx+wbry)%2==0)
                g.drawImage(wb1,square*wbrx+60,square*wbry+60,square-20,square-20,this);
            else
                g.drawImage(wb2,square*wbrx+60,square*wbry+60,square-20,square-20,this);
        }
        if((wkx!=-1)&&(wky!=-1))
        {
            if(!wcheck&&!wmate&&!wstale)
            {
                if((wkx+wky)%2==0)
                    g.drawImage(wk1,square*wkx+60,square*wky+60,square-20,square-20,this);
                else
                    g.drawImage(wk2,square*wkx+60,square*wky+60,square-20,square-20,this);
            }
            else if(wmate)
            {
                g.setColor(new Color(255,0,0));
                g.fillRect(square*wkx+50,square*wky+50,square,square);
                g.drawImage(wk4,square*wkx+60,square*wky+60,square-20,square-20,this);
            }
            else if(wcheck)
            {
                g.setColor(new Color(255,255,0));
                g.fillRect(square*wkx+50,square*wky+50,square,square);
                g.drawImage(wk3,square*wkx+60,square*wky+60,square-20,square-20,this);
            }
            else if(wstale)
            {
                g.setColor(new Color(255,160,0));
                g.fillRect(square*wkx+50,square*wky+50,square,square);
                g.drawImage(wk5,square*wkx+60,square*wky+60,square-20,square-20,this);
            }
        }
        if((bkx!=-1)&&(bky!=-1))
        {
            if(!bcheck&&!bmate&&!bstale)
            {
                if((bkx+bky)%2==0)
                    g.drawImage(bk1,square*bkx+60,square*bky+60,square-20,square-20,this);
                else
                    g.drawImage(bk2,square*bkx+60,square*bky+60,square-20,square-20,this);    
            }
            else if(bmate)
            {
                g.setColor(new Color(255,0,0));
                g.fillRect(square*bkx+50,square*bky+50,square,square);
                g.drawImage(bk4,square*bkx+60,square*bky+60,square-20,square-20,this);
            }
            else if(bcheck)
            {
                g.setColor(new Color(255,255,0));
                g.fillRect(square*bkx+50,square*bky+50,square,square);
                g.drawImage(bk3,square*bkx+60,square*bky+60,square-20,square-20,this);
            }
            else if(bstale)
            {
                g.setColor(new Color(255,160,0));
                g.fillRect(square*bkx+50,square*bky+50,square,square);
                g.drawImage(bk5,square*bkx+60,square*bky+60,square-20,square-20,this);
            }
        }            
        if((wqx!=-1)&&(wqy!=-1))
        {
            if((wqx+wqy)%2==0)
                g.drawImage(wq1,square*wqx+60,square*wqy+60,square-20,square-20,this);
            else
                g.drawImage(wq2,square*wqx+60,square*wqy+60,square-20,square-20,this);
        }        
        if((bqx!=-1)&&(bqy!=-1))
        {
            if((bqx+bqy)%2==0)
                g.drawImage(bq1,square*bqx+60,square*bqy+60,square-20,square-20,this);
            else
                g.drawImage(bq2,square*bqx+60,square*bqy+60,square-20,square-20,this);
        }
        for(int i=8;i<16;i++)
        {
            int a = piece[i][0];
            int b = piece[i][1];
            if((a!=-1)&&(b!=-1))
            {
                if((a+b)%2==0)
                    g.drawImage(bp1,a*square+65,b*square+60,square-30,square-20,this);
                else if((a+b)%2==1)
                    g.drawImage(bp2,a*square+65,b*square+60,square-30,square-20,this);
            }
        }
        for(int i=16;i<24;i++)
        {
            int a = piece[i][0];
            int b = piece[i][1];
            if((a!=-1)&&(b!=-1))
            {
                if((a+b)%2==0)
                    g.drawImage(wp1,a*square+65,b*square+60,square-30,square-20,this);
                else if((a+b)%2==1)
                    g.drawImage(wp2,a*square+65,b*square+60,square-30,square-20,this);
            }
        }
        if((weq1x!=-1)&&(weq1y!=-1))
        {
            if((weq1x+weq1y)%2==0)
                g.drawImage(wq1,square*weq1x+60,square*weq1y+60,square-20,square-20,this);
            else
                g.drawImage(wq2,square*weq1x+60,square*weq1y+60,square-20,square-20,this);
        }
        if((weq2x!=-1)&&(weq2y!=-1))
        {
            if((weq2x+weq2y)%2==0)
                g.drawImage(wq1,square*weq2x+60,square*weq2y+60,square-20,square-20,this);
            else
                g.drawImage(wq2,square*weq2x+60,square*weq2y+60,square-20,square-20,this);
        }
        if((weq3x!=-1)&&(weq3y!=-1))
        {
            if((weq3x+weq3y)%2==0)
                g.drawImage(wq1,square*weq3x+60,square*weq3y+60,square-20,square-20,this);
            else
                g.drawImage(wq2,square*weq3x+60,square*weq3y+60,square-20,square-20,this);
        }
        if((weq4x!=-1)&&(weq4y!=-1))
        {
            if((weq4x+weq4y)%2==0)
                g.drawImage(wq1,square*weq4x+60,square*weq4y+60,square-20,square-20,this);
            else
                g.drawImage(wq2,square*weq4x+60,square*weq4y+60,square-20,square-20,this);
        }
        if((beq1x!=-1)&&(beq1y!=-1))
        {
            if((beq1x+beq1y)%2==0)
                g.drawImage(bq1,square*beq1x+60,square*beq1y+60,square-20,square-20,this);
            else
                g.drawImage(bq2,square*beq1x+60,square*beq1y+60,square-20,square-20,this);
        }
        if((beq2x!=-1)&&(beq2y!=-1))
        {
            if((beq2x+beq2y)%2==0)
                g.drawImage(bq1,square*beq2x+60,square*beq2y+60,square-20,square-20,this);
            else
                g.drawImage(bq2,square*beq2x+60,square*beq2y+60,square-20,square-20,this);
        }
        if((beq3x!=-1)&&(beq3y!=-1))
        {
            if((beq3x+beq3y)%2==0)
                g.drawImage(bq1,square*beq3x+60,square*beq3y+60,square-20,square-20,this);
            else
                g.drawImage(bq2,square*beq3x+60,square*beq3y+60,square-20,square-20,this);
        }
        if((beq4x!=-1)&&(beq4y!=-1))
        {
            if((beq4x+beq4y)%2==0)
                g.drawImage(bq1,square*beq4x+60,square*beq4y+60,square-20,square-20,this);
            else
                g.drawImage(bq2,square*beq4x+60,square*beq4y+60,square-20,square-20,this);
        }
        if(selected)
        {
            g.setColor(Color.blue);
            g.drawRect(piece[focus][0]*square+50,piece[focus][1]*square+50,square,square);
            g.drawRect(piece[focus][0]*square+51,piece[focus][1]*square+51,square-2,square-2);
            g.drawRect(piece[focus][0]*square+52,piece[focus][1]*square+52,square-4,square-4);
            g.drawRect(piece[focus][0]*square+53,piece[focus][1]*square+53,square-6,square-6);
            for(int i=0;i<c;i++)
            {
                g.drawRect(moves[i][0]*square+50,moves[i][1]*square+50,square,square);
                g.drawRect(moves[i][0]*square+51,moves[i][1]*square+51,square-2,square-2);
                g.drawRect(moves[i][0]*square+52,moves[i][1]*square+52,square-4,square-4);                
                g.drawRect(moves[i][0]*square+53,moves[i][1]*square+53,square-6,square-6);
            }
            g.setColor(new Color(255,0,255));
            for(int i=0;i<k;i++)
            {
                g.drawRect(castmoves[i][0]*square+50,castmoves[i][1]*square+50,square,square);
                g.drawRect(castmoves[i][0]*square+51,castmoves[i][1]*square+51,square-2,square-2);
                g.drawRect(castmoves[i][0]*square+52,castmoves[i][1]*square+52,square-4,square-4);
                g.drawRect(castmoves[i][0]*square+53,castmoves[i][1]*square+53,square-6,square-6);
            }
        }    
        g.setColor(Color.black);
        for(int i=0;i<=8;i++)
        {
            g.drawLine(50+i*square,50,50+i*square,50+square*8);
            g.drawLine(50,50+i*square,50+square*8,50+i*square);
        }
        if(turn==0&&!bmate&&!draw)
        {
            g.setColor(Color.black);
            g.fillRect(50,25,105,20);
            g.setColor(Color.white);
            g.setFont(new Font("Times New Roman",Font.PLAIN,18));   
            g.drawString("Move : Black",55,41);
        }
        else if(turn==1&&!wmate&&!draw)
        {
            g.setColor(Color.black);
            g.drawRect(50,55+square*8,111,20);            
            g.setFont(new Font("Times New Roman",Font.PLAIN,18));
            g.drawString("Move : White",55,71+square*8);
        }
        if(bmate&&!preview)
        {
            try
            {
                Thread.sleep(1000);
            }
            catch(Exception e){}
            g.setColor(Color.yellow);
            g.fillRoundRect(51+square*2+(square-60)*2,51+square*3+square-60,239,119,50,50);
            g.setFont(new Font("Cambria",3,16));
            g.setColor(Color.blue);
            g.drawString("CHECKMATE!!",120+square*2+(square-60)*2,68+square*3+square-60);
            g.drawString("White Wins.",125+square*2+(square-60)*2,88+square*3+square-60);
            g.setColor(Color.blue);
            g.fillRect(80+square*2+(square-60)*2,100+square*3+square-60,180,25);            
            g.fillRect(80+square*2+(square-60)*2,132+square*3+square-60,180,25);
            g.setColor(Color.white);
            g.drawString("View the Board",115+square*2+(square-60)*2,118+square*3+square-60);
            g.drawString("Play Again",130+square*2+(square-60)*2,150+square*3+square-60);
        }
        if(wmate&&!preview)
        {
            try
            {
                Thread.sleep(1000);
            }
            catch(Exception e){}
            g.setColor(Color.yellow);
            g.fillRoundRect(51+square*2+(square-60)*2,51+square*3+square-60,239,119,50,50);
            g.setFont(new Font("Cambria",3,16));
            g.setColor(Color.blue);
            g.drawString("CHECKMATE!!",120+square*2+(square-60)*2,68+square*3+square-60);
            g.drawString("Black Wins.",125+square*2+(square-60)*2,88+square*3+square-60);
            g.setColor(Color.blue);
            g.fillRect(80+square*2+(square-60)*2,100+square*3+square-60,180,25);            
            g.fillRect(80+square*2+(square-60)*2,132+square*3+square-60,180,25);
            g.setColor(Color.white);
            g.drawString("View the Board",115+square*2+(square-60)*2,118+square*3+square-60);
            g.drawString("Play Again",130+square*2+(square-60)*2,150+square*3+square-60);
        }
        if((bstale||wstale)&&!preview)
        {
            try
            {
                Thread.sleep(1000);
            }
            catch(Exception e){}
            g.setColor(Color.yellow);
            g.fillRoundRect(51+square*2+(square-60)*2,51+square*3+square-60,239,119,50,50);
            g.setFont(new Font("Cambria",3,16));
            g.setColor(Color.blue);
            g.drawString("STALEMATE!!",120+square*2+(square-60)*2,68+square*3+square-60);
            g.drawString("It is a draw.",125+square*2+(square-60)*2,88+square*3+square-60);
            g.setColor(Color.blue);
            g.fillRect(80+square*2+(square-60)*2,100+square*3+square-60,180,25);            
            g.fillRect(80+square*2+(square-60)*2,132+square*3+square-60,180,25);
            g.setColor(Color.white);
            g.drawString("View the Board",115+square*2+(square-60)*2,118+square*3+square-60);
            g.drawString("Play Again",130+square*2+(square-60)*2,150+square*3+square-60);
        }
        if(draw&&!preview)
        {
            try
            {
                Thread.sleep(1000);
            }
            catch(Exception e){}
            g.setColor(Color.yellow);
            g.fillRoundRect(51+square*2+(square-60)*2,51+square*3+square-60,239,119,50,50);
            g.setFont(new Font("Cambria",3,16));
            g.setColor(Color.blue);
            g.drawString("INSUFFICIENT PIECES!!",90+square*2+(square-60)*2,68+square*3+square-60);
            g.drawString("It is a draw.",130+square*2+(square-60)*2,88+square*3+square-60);
            g.setColor(Color.blue);
            g.fillRect(80+square*2+(square-60)*2,100+square*3+square-60,180,25);            
            g.fillRect(80+square*2+(square-60)*2,132+square*3+square-60,180,25);
            g.setColor(Color.white);
            g.drawString("View the Board",115+square*2+(square-60)*2,118+square*3+square-60);
            g.drawString("Play Again",130+square*2+(square-60)*2,150+square*3+square-60);
        }
    }

    public void update(Graphics g)
    {
        paint(g);
    }

    void generateMoves(int x,int y)
    {  
        c=0;
        k=0;
        for(int i=0;i<37;i++)
        {
            moves[i][0]=-1;
            moves[i][1]=-1;
        }
        for(int i=0;i<2;i++)
        {
            castmoves[i][0]=-1;
            castmoves[i][1]=-1;
        }
        int i = board[x][y];
        if((i>7)&&(i<16))
        {
            boolean p = true;
            boolean cl = false;
            boolean cr = false;          
            if(board[x][y+1]!=-1)
                p=false;
            if(x<7)
            {
                if(board[x+1][y+1]!=-1&&((board[x+1][y+1]>15&&board[x+1][y+1]<=31)||(board[x+1][y+1]>35&&board[x+1][y+1]<40)))
                    cr=true;
            }
            if(x>0)
            {
                if(board[x-1][y+1]!=-1&&((board[x-1][y+1]>15&&board[x-1][y+1]<=31)||(board[x-1][y+1]>35&&board[x-1][y+1]<40)))
                    cl=true;
            }
            if(y==1)
            {
                if(p&&(board[x][y+2]==-1)&&validate(i,x,y+2))
                {
                    moves[c][0]=x;
                    moves[c][1]=y+2;
                    c++;
                }
            }
            if(p&&validate(i,x,y+1))
            {
                moves[c][0]=x;
                moves[c][1]=y+1;
                c++;
            }
            if(cl&&validate(i,x-1,y+1))
            {
                moves[c][0]=x-1;
                moves[c][1]=y+1;
                c++;
            }
            if(cr&&validate(i,x+1,y+1))
            {
                moves[c][0]=x+1;
                moves[c][1]=y+1;
                c++;
            }            
        }
        else if((i>=16)&&(i<=23))
        {
            boolean p = true;
            boolean cl = false;
            boolean cr = false;
            if(board[x][y-1]!=-1)
                p=false;
            if(x<7)
            {
                if(board[x+1][y-1]!=-1&&(board[x+1][y-1]<=15||(board[x+1][y-1]>=32&&board[x+1][y-1]<=35)))
                    cr=true;
            }
            if(x>0)
            {
                if(board[x-1][y-1]!=-1&&(board[x-1][y-1]<=15||(board[x-1][y-1]>=32&&board[x-1][y-1]<=35)))
                    cl=true;
            }
            if(y==6)
            {
                if(p&&(board[x][y-2]==-1)&&validate(i,x,y-2))
                {
                    moves[c][0]=x;
                    moves[c][1]=y-2;
                    c++;
                }
            }
            if(p&&validate(i,x,y-1))
            {
                moves[c][0]=x;
                moves[c][1]=y-1;
                c++;
            }
            if(cl&&validate(i,x-1,y-1))
            {
                moves[c][0]=x-1;
                moves[c][1]=y-1;
                c++;
            }
            if(cr&&validate(i,x+1,y-1))
            {
                moves[c][0]=x+1;
                moves[c][1]=y-1;
                c++;
            }            
        }
        else if(i==0||i==7)
        {
            int l=x;
            for(;l>=0;l--)
            {
                if((board[l][y]!=-1)&&(board[l][y]!=i))
                {
                    if(((board[l][y]>15&&board[l][y]<=31)||(board[l][y]>35&&board[l][y]<40))&&validate(i,l,y))
                    {
                        moves[c][0]=l;
                        moves[c][1]=y;
                        c++;
                    }
                    break;
                }
                if(validate(i,l,y))
                {
                    moves[c][0]=l;
                    moves[c][1]=y;
                    c++;
                }
            }
            int r=x;
            for(;r<=7;r++)
            {
                if((board[r][y]!=-1)&&(board[r][y]!=i))
                {
                    if(((board[r][y]>15&&board[r][y]<=31)||(board[r][y]>35&&board[r][y]<40))&&validate(i,r,y))
                    {
                        moves[c][0]=r;
                        moves[c][1]=y;
                        c++;
                    }
                    break;
                }
                if(validate(i,r,y))
                {
                    moves[c][0]=r;
                    moves[c][1]=y;
                    c++;
                }
            }
            int u=y;
            for(;u>=0;u--)
            {
                if((board[x][u]!=-1)&&(board[x][u]!=i))
                {
                    if(((board[x][u]>15&&board[x][u]<=31)||(board[x][u]>35&&board[x][u]<40))&&validate(i,x,u))
                    {
                        moves[c][0]=x;
                        moves[c][1]=u;
                        c++;
                    }
                    break;
                }
                if(validate(i,x,u))
                {
                    moves[c][0]=x;
                    moves[c][1]=u;
                    c++;
                }
            }
            int d=y;
            for(;d<=7;d++)
            {
                if((board[x][d]!=-1)&&(board[x][d]!=i))
                {
                    if(((board[x][d]>15&&board[x][d]<=31)||(board[x][d]>35&&board[x][d]<40))&&validate(i,x,d))
                    {
                        moves[c][0]=x;
                        moves[c][1]=d;
                        c++;
                    }
                    break;
                }
                if(validate(i,x,d))
                {
                    moves[c][0]=x;
                    moves[c][1]=d;
                    c++;
                }
            }
        }
        else if(i==24||i==31)
        {
            int l=x;
            for(;l>=0;l--)
            {
                if((board[l][y]!=-1)&&(board[l][y]!=i))
                {
                    if((board[l][y]<=15||(board[l][y]>=32&&board[l][y]<=35))&&validate(i,l,y))
                    {
                        moves[c][0]=l;
                        moves[c][1]=y;
                        c++;
                    }
                    break;
                }
                if(validate(i,l,y))
                {
                    moves[c][0]=l;
                    moves[c][1]=y;
                    c++;
                }
            }
            int r=x;
            for(;r<=7;r++)
            {
                if((board[r][y]!=-1)&&(board[r][y]!=i))
                {
                    if((board[r][y]<=15||(board[r][y]>=32&&board[r][y]<=35))&&validate(i,r,y))
                    {
                        moves[c][0]=r;
                        moves[c][1]=y;
                        c++;
                    }
                    break;
                }
                if(validate(i,r,y))
                {
                    moves[c][0]=r;
                    moves[c][1]=y;
                    c++;
                }
            }
            int u=y;
            for(;u>=0;u--)
            {
                if((board[x][u]!=-1)&&(board[x][u]!=i))
                {
                    if((board[x][u]<=15||(board[x][u]>=32&&board[x][u]<=35))&&validate(i,x,u))
                    {
                        moves[c][0]=x;
                        moves[c][1]=u;
                        c++;
                    }
                    break;
                }
                if(validate(i,x,u))
                {
                    moves[c][0]=x;
                    moves[c][1]=u;
                    c++;
                }
            }
            int d=y;
            for(;d<=7;d++)
            {
                if((board[x][d]!=-1)&&(board[x][d]!=i))
                {
                    if((board[x][d]<=15||(board[x][d]>=32&&board[x][d]<=35))&&validate(i,x,d))
                    {
                        moves[c][0]=x;
                        moves[c][1]=d;
                        c++;
                    }
                    break;
                }
                if(validate(i,x,d))
                {
                    moves[c][0]=x;
                    moves[c][1]=d;
                    c++;
                }
            }
        }
        else if(i==1||i==6)
        {
            if((x>1)&&(y>0))
            {
                if(((board[x-2][y-1]<0)||((board[x-2][y-1]>15&&board[x-2][y-1]<=31)||(board[x-2][y-1]>35&&board[x-2][y-1]<40)))&&validate(i,x-2,y-1))
                {
                    moves[c][0]=x-2;
                    moves[c][1]=y-1;
                    c++;
                }
            }
            if((x>1)&&(y<7))
            {
                if(((board[x-2][y+1]<0)||((board[x-2][y+1]>15&&board[x-1][y+1]<=31)||(board[x-2][y+1]>35&&board[x-2][y+1]<40)))&&validate(i,x-2,y+1))
                {
                    moves[c][0]=x-2;
                    moves[c][1]=y+1;
                    c++;
                }
            }
            if((x<6)&&(y>0))
            {
                if(((board[x+2][y-1]<0)||((board[x+2][y-1]>15&&board[x+2][y-1]<=31)||(board[x+2][y-1]>35&&board[x+2][y-1]<40)))&&validate(i,x+2,y-1))
                {
                    moves[c][0]=x+2;
                    moves[c][1]=y-1;
                    c++;
                }
            }
            if((x<6)&&(y<7))
            {
                if(((board[x+2][y+1]<0)||((board[x+2][y+1]>15&&board[x+2][y+1]<=31)||(board[x+2][y+1]>35&&board[x+2][y+1]<40)))&&validate(i,x+2,y+1))
                {
                    moves[c][0]=x+2;
                    moves[c][1]=y+1;
                    c++;
                }
            }
            if((x>0)&&(y>1))
            {
                if(((board[x-1][y-2]<0)||((board[x-1][y-2]>15&&board[x-1][y-2]<=31)||(board[x-1][y-2]>35&&board[x-1][y-2]<40)))&&validate(i,x-1,y-2))
                {
                    moves[c][0]=x-1;
                    moves[c][1]=y-2;
                    c++;
                }
            }
            if((x>0)&&(y<6))
            {
                if(((board[x-1][y+2]<0)||((board[x-1][y+2]>15&&board[x-1][y+2]<=31)||(board[x-1][y+2]>35&&board[x-1][y+2]<40)))&&validate(i,x-1,y+2))
                {
                    moves[c][0]=x-1;
                    moves[c][1]=y+2;
                    c++;
                }
            }
            if((x<7)&&(y>1))
            {
                if(((board[x+1][y-2]<0)||((board[x+1][y-2]>15&&board[x+1][y-2]<=31)||(board[x+1][y-2]>35&&board[x+1][y-2]<40)))&&validate(i,x+1,y-2))
                {
                    moves[c][0]=x+1;
                    moves[c][1]=y-2;
                    c++;
                }
            }
            if((x<7)&&(y<6))
            {
                if(((board[x+1][y+2]<0)||((board[x+1][y+2]>15&&board[x+1][y+2]<=31)||(board[x+1][y+2]>35&&board[x+1][y+2]<40)))&&validate(i,x+1,y+2))
                {
                    moves[c][0]=x+1;
                    moves[c][1]=y+2;
                    c++;
                }
            }
        }
        else if(i==25||i==30)
        {
            if((x>1)&&(y>0))
            {
                if(((board[x-2][y-1]<0)||(board[x-2][y-1]<=15||(board[x-2][y-1]>=32&&board[x-2][y-1]<=35)))&&validate(i,x-2,y-1))
                {
                    moves[c][0]=x-2;
                    moves[c][1]=y-1;
                    c++;
                }
            }
            if((x>1)&&(y<7))
            {
                if(((board[x-2][y+1]<0)||(board[x-2][y+1]<=15||(board[x-2][y+1]>=32&&board[x-2][y+1]<=35)))&&validate(i,x-2,y+1))
                {
                    moves[c][0]=x-2;
                    moves[c][1]=y+1;
                    c++;
                }
            }
            if((x<6)&&(y>0))
            {
                if(((board[x+2][y-1]<0)||(board[x+2][y-1]<=15||(board[x+2][y-1]>=32&&board[x+2][y-1]<=35)))&&validate(i,x+2,y-1))
                {
                    moves[c][0]=x+2;
                    moves[c][1]=y-1;
                    c++;
                }
            }
            if((x<6)&&(y<7))
            {
                if(((board[x+2][y+1]<0)||(board[x+2][y+1]<=15||(board[x+2][y+1]>=32&&board[x+2][y+1]<=35)))&&validate(i,x+2,y+1))
                {
                    moves[c][0]=x+2;
                    moves[c][1]=y+1;
                    c++;
                }
            }
            if((x>0)&&(y>1))
            {
                if(((board[x-1][y-2]<0)||(board[x-1][y-2]<=15||(board[x-1][y-2]>=32&&board[x-1][y-2]<=35)))&&validate(i,x-1,y-2))
                {
                    moves[c][0]=x-1;
                    moves[c][1]=y-2;
                    c++;
                }
            }
            if((x>0)&&(y<6))
            {
                if(((board[x-1][y+2]<0)||(board[x-1][y+2]<=15||(board[x-1][y+2]>=32&&board[x-1][y+2]<=35)))&&validate(i,x-1,y+2))
                {
                    moves[c][0]=x-1;
                    moves[c][1]=y+2;
                    c++;
                }
            }
            if((x<7)&&(y>1))
            {
                if(((board[x+1][y-2]<0)||(board[x+1][y-2]<=15||(board[x+1][y-2]>=32&&board[x+1][y-2]<=35)))&&validate(i,x+1,y-2))
                {
                    moves[c][0]=x+1;
                    moves[c][1]=y-2;
                    c++;
                }
            }
            if((x<7)&&(y<6))
            {
                if(((board[x+1][y+2]<0)||(board[x+1][y+2]<=15||(board[x+1][y+2]>=32&&board[x+1][y+2]<=35)))&&validate(i,x+1,y+2))
                {
                    moves[c][0]=x+1;
                    moves[c][1]=y+2;
                    c++;
                }
            }
        }
        else if(i==2||i==5)
        {
            int ld[]={x,y};
            while((ld[0]!=-1)&&(ld[1]!=8))
            {
                if((board[ld[0]][ld[1]]!=-1)&&(board[ld[0]][ld[1]]!=i))
                {
                    if(((board[ld[0]][ld[1]]>15&&board[ld[0]][ld[1]]<=31)||(board[ld[0]][ld[1]]>35&&board[ld[0]][ld[1]]<40))&&validate(i,ld[0],ld[1]))
                    {
                        moves[c][0]=ld[0];
                        moves[c][1]=ld[1];
                        c++;
                    }
                    break;
                }
                else
                {
                    if(validate(i,ld[0],ld[1]))
                    {
                        moves[c][0]=ld[0];
                        moves[c][1]=ld[1];
                        c++;
                    }
                    ld[0]--;
                    ld[1]++;
                }
            }
            int rd[]={x,y};
            while((rd[0]!=8)&&(rd[1]!=8))
            {
                if((board[rd[0]][rd[1]]!=-1)&&(board[rd[0]][rd[1]]!=i))
                {
                    if(((board[rd[0]][rd[1]]>15&&board[rd[0]][rd[1]]<=31)||(board[rd[0]][rd[1]]>35&&board[rd[0]][rd[1]]<40))&&validate(i,rd[0],rd[1]))
                    {
                        moves[c][0]=rd[0];
                        moves[c][1]=rd[1];
                        c++;
                    }
                    break;
                }
                else
                {
                    if(validate(i,rd[0],rd[1]))
                    {
                        moves[c][0]=rd[0];
                        moves[c][1]=rd[1];
                        c++;
                    }                   
                    rd[0]++;
                    rd[1]++;
                }
            }
            int lu[]={x,y};
            while((lu[0]!=-1)&&(lu[1]!=-1))
            {
                if((board[lu[0]][lu[1]]!=-1)&&(board[lu[0]][lu[1]]!=i))
                {
                    if(((board[lu[0]][lu[1]]>15&&board[lu[0]][lu[1]]<=31)||(board[lu[0]][lu[1]]>35&&board[lu[0]][lu[1]]<40))&&validate(i,lu[0],lu[1]))
                    {
                        moves[c][0]=lu[0];
                        moves[c][1]=lu[1];
                        c++;
                    }
                    break;
                }
                else
                {
                    if(validate(i,lu[0],lu[1]))
                    {
                        moves[c][0]=lu[0];
                        moves[c][1]=lu[1];
                        c++;
                    }
                    lu[0]--;
                    lu[1]--;
                }
            }
            int ru[]={x,y};
            while((ru[0]!=8)&&(ru[1]!=-1))
            {
                if((board[ru[0]][ru[1]]!=-1)&&(board[ru[0]][ru[1]]!=i))
                {
                    if(((board[ru[0]][ru[1]]>15&&board[ru[0]][ru[1]]<=31)||(board[ru[0]][ru[1]]>35&&board[ru[0]][ru[1]]<40))&&validate(i,ru[0],ru[1]))
                    {
                        moves[c][0]=ru[0];
                        moves[c][1]=ru[1];
                        c++;
                    }
                    break;
                }
                else
                {
                    if(validate(i,ru[0],ru[1]))
                    {
                        moves[c][0]=ru[0];
                        moves[c][1]=ru[1];
                        c++;
                    }
                    ru[0]++;
                    ru[1]--;
                }
            }
        }
        else if(i==26||i==29)
        {
            int ld[]={x,y};
            while((ld[0]!=-1)&&(ld[1]!=8))
            {                
                if((board[ld[0]][ld[1]]!=-1)&&(board[ld[0]][ld[1]]!=i))
                {
                    if((board[ld[0]][ld[1]]<=15||(board[ld[0]][ld[1]]>=32&&board[ld[0]][ld[1]]<=35))&&validate(i,ld[0],ld[1]))
                    {
                        moves[c][0]=ld[0];
                        moves[c][1]=ld[1];
                        c++;
                    }
                    break;
                }
                else
                {
                    if(validate(i,ld[0],ld[1]))
                    {
                        moves[c][0]=ld[0];
                        moves[c][1]=ld[1];
                        c++;
                    }
                    ld[0]--;
                    ld[1]++;
                }
            }
            int rd[]={x,y};
            while((rd[0]!=8)&&(rd[1]!=8))
            {
                if((board[rd[0]][rd[1]]!=-1)&&(board[rd[0]][rd[1]]!=i))
                {
                    if((board[rd[0]][rd[1]]<=15||(board[rd[0]][rd[1]]>=32&&board[rd[0]][rd[1]]<=35))&&validate(i,rd[0],rd[1]))
                    {
                        moves[c][0]=rd[0];
                        moves[c][1]=rd[1];
                        c++;
                    }
                    break;
                }
                else
                {
                    if(validate(i,rd[0],rd[1]))
                    {
                        moves[c][0]=rd[0];
                        moves[c][1]=rd[1];
                        c++;
                    }
                    rd[0]++;
                    rd[1]++;
                }
            }
            int lu[]={x,y};
            while((lu[0]!=-1)&&(lu[1]!=-1))
            {
                if((board[lu[0]][lu[1]]!=-1)&&(board[lu[0]][lu[1]]!=i))
                {
                    if((board[lu[0]][lu[1]]<=15||(board[lu[0]][lu[1]]>=32&&board[lu[0]][lu[1]]<=35))&&validate(i,lu[0],lu[1]))
                    {
                        moves[c][0]=lu[0];
                        moves[c][1]=lu[1];
                        c++;
                    }
                    break;
                }
                else
                {
                    if(validate(i,lu[0],lu[1]))
                    {
                        moves[c][0]=lu[0];
                        moves[c][1]=lu[1];
                        c++;
                    }
                    lu[0]--;
                    lu[1]--;
                }
            }
            int ru[]={x,y};
            while((ru[0]!=8)&&(ru[1]!=-1))
            {
                if((board[ru[0]][ru[1]]!=-1)&&(board[ru[0]][ru[1]]!=i))
                {
                    if((board[ru[0]][ru[1]]<=15||(board[ru[0]][ru[1]]>=32&&board[ru[0]][ru[1]]<=35))&&validate(i,ru[0],ru[1]))
                    {
                        moves[c][0]=ru[0];
                        moves[c][1]=ru[1];
                        c++;
                    }
                    break;
                }
                else
                {
                    if(validate(i,ru[0],ru[1]))
                    {
                        moves[c][0]=ru[0];
                        moves[c][1]=ru[1];
                        c++;
                    }
                    ru[0]++;
                    ru[1]--;
                }
            }
        }
        else if(i==3||(i>=32&&i<36))
        {
            int l=x;
            for(;l>=0;l--)
            {
                if((board[l][y]!=-1)&&(board[l][y]!=i))
                {
                    if(((board[l][y]>15&&board[l][y]<=31)||(board[l][y]>35&&board[l][y]<40))&&validate(i,l,y))
                    {
                        moves[c][0]=l;
                        moves[c][1]=y;
                        c++;
                    }
                    break;
                }
                if(validate(i,l,y))
                {
                    moves[c][0]=l;
                    moves[c][1]=y;
                    c++;
                }
            }
            int r=x;
            for(;r<=7;r++)
            {
                if((board[r][y]!=-1)&&(board[r][y]!=i))
                {
                    if(((board[r][y]>15&&board[r][y]<=31)||(board[r][y]>35&&board[r][y]<40))&&validate(i,r,y))
                    {
                        moves[c][0]=r;
                        moves[c][1]=y;
                        c++;
                    }
                    break;
                }
                if(validate(i,r,y))
                {
                    moves[c][0]=r;
                    moves[c][1]=y;
                    c++;
                }
            }
            int u=y;
            for(;u>=0;u--)
            {
                if((board[x][u]!=-1)&&(board[x][u]!=i))
                {
                    if(((board[x][u]>15&&board[x][u]<=31)||(board[x][u]>35&&board[x][u]<40))&&validate(i,x,u))
                    {
                        moves[c][0]=x;
                        moves[c][1]=u;
                        c++;
                    }
                    break;
                }
                if(validate(i,x,u))
                {
                    moves[c][0]=x;
                    moves[c][1]=u;
                    c++;
                }
            }
            int d=y;
            for(;d<=7;d++)
            {
                if((board[x][d]!=-1)&&(board[x][d]!=i))
                {
                    if(((board[x][d]>15&&board[x][d]<=31)||(board[x][d]>35&&board[x][d]<40))&&validate(i,x,d))
                    {
                        moves[c][0]=x;
                        moves[c][1]=d;
                        c++;
                    }
                    break;
                }
                if(validate(i,x,d))
                {
                    moves[c][0]=x;
                    moves[c][1]=d;
                    c++;
                }
            }
            int ld[]={x,y};
            while((ld[0]!=-1)&&(ld[1]!=8))
            {
                if((board[ld[0]][ld[1]]!=-1)&&(board[ld[0]][ld[1]]!=i))
                {
                    if(((board[ld[0]][ld[1]]>15&&board[ld[0]][ld[1]]<=31)||(board[ld[0]][ld[1]]>35&&board[ld[0]][ld[1]]<40))&&validate(i,ld[0],ld[1]))
                    {
                        moves[c][0]=ld[0];
                        moves[c][1]=ld[1];
                        c++;
                    }
                    break;
                }
                else
                {
                    if(validate(i,ld[0],ld[1]))
                    {
                        moves[c][0]=ld[0];
                        moves[c][1]=ld[1];
                        c++;
                    }
                    ld[0]--;
                    ld[1]++;
                }
            }
            int rd[]={x,y};
            while((rd[0]!=8)&&(rd[1]!=8))
            {
                if((board[rd[0]][rd[1]]!=-1)&&(board[rd[0]][rd[1]]!=i))
                {
                    if(((board[rd[0]][rd[1]]>15&&board[rd[0]][rd[1]]<=31)||(board[rd[0]][rd[1]]>35&&board[rd[0]][rd[1]]<40))&&validate(i,rd[0],rd[1]))
                    {
                        moves[c][0]=rd[0];
                        moves[c][1]=rd[1];
                        c++;
                    }
                    break;
                }
                else
                {
                    if(validate(i,rd[0],rd[1]))
                    {
                        moves[c][0]=rd[0];
                        moves[c][1]=rd[1];
                        c++;
                    }                   
                    rd[0]++;
                    rd[1]++;
                }
            }
            int lu[]={x,y};
            while((lu[0]!=-1)&&(lu[1]!=-1))
            {
                if((board[lu[0]][lu[1]]!=-1)&&(board[lu[0]][lu[1]]!=i))
                {
                    if(((board[lu[0]][lu[1]]>15&&board[lu[0]][lu[1]]<=31)||(board[lu[0]][lu[1]]>35&&board[lu[0]][lu[1]]<40))&&validate(i,lu[0],lu[1]))
                    {
                        moves[c][0]=lu[0];
                        moves[c][1]=lu[1];
                        c++;
                    }
                    break;
                }
                else
                {
                    if(validate(i,lu[0],lu[1]))
                    {
                        moves[c][0]=lu[0];
                        moves[c][1]=lu[1];
                        c++;
                    }
                    lu[0]--;
                    lu[1]--;
                }
            }
            int ru[]={x,y};
            while((ru[0]!=8)&&(ru[1]!=-1))
            {
                if((board[ru[0]][ru[1]]!=-1)&&(board[ru[0]][ru[1]]!=i))
                {
                    if(((board[ru[0]][ru[1]]>15&&board[ru[0]][ru[1]]<=31)||(board[ru[0]][ru[1]]>35&&board[ru[0]][ru[1]]<40))&&validate(i,ru[0],ru[1]))
                    {
                        moves[c][0]=ru[0];
                        moves[c][1]=ru[1];
                        c++;
                    }
                    break;
                }
                else
                {
                    if(validate(i,ru[0],ru[1]))
                    {
                        moves[c][0]=ru[0];
                        moves[c][1]=ru[1];
                        c++;
                    }
                    ru[0]++;
                    ru[1]--;
                }
            }
        }
        else if(i==27||(i>=36&&i<40))
        {
            int l=x;
            for(;l>=0;l--)
            {
                if((board[l][y]!=-1)&&(board[l][y]!=i))
                {
                    if((board[l][y]<=15||(board[l][y]>=32&&board[l][y]<=35))&&validate(i,l,y))
                    {
                        moves[c][0]=l;
                        moves[c][1]=y;
                        c++;
                    }
                    break;
                }
                if(validate(i,l,y))
                {
                    moves[c][0]=l;
                    moves[c][1]=y;
                    c++;
                }
            }
            int r=x;
            for(;r<=7;r++)
            {
                if((board[r][y]!=-1)&&(board[r][y]!=i))
                {
                    if((board[r][y]<=15||(board[r][y]>=32&&board[r][y]<=35))&&validate(i,r,y))
                    {
                        moves[c][0]=r;
                        moves[c][1]=y;
                        c++;
                    }
                    break;
                }
                if(validate(i,r,y))
                {
                    moves[c][0]=r;
                    moves[c][1]=y;
                    c++;
                }
            }
            int u=y;
            for(;u>=0;u--)
            {
                if((board[x][u]!=-1)&&(board[x][u]!=i))
                {
                    if((board[x][u]<=15||(board[x][u]>=32&&board[x][u]<=35))&&validate(i,x,u))
                    {
                        moves[c][0]=x;
                        moves[c][1]=u;
                        c++;
                    }
                    break;
                }
                if(validate(i,x,u))
                {
                    moves[c][0]=x;
                    moves[c][1]=u;
                    c++;
                }
            }
            int d=y;
            for(;d<=7;d++)
            {
                if((board[x][d]!=-1)&&(board[x][d]!=i))
                {
                    if((board[x][d]<=15||(board[x][d]>=32&&board[x][d]<=35))&&validate(i,x,d))
                    {
                        moves[c][0]=x;
                        moves[c][1]=d;
                        c++;
                    }
                    break;
                }
                if(validate(i,x,d))
                {
                    moves[c][0]=x;
                    moves[c][1]=d;
                    c++;
                }
            }
            int ld[]={x,y};
            while((ld[0]!=-1)&&(ld[1]!=8))
            {                
                if((board[ld[0]][ld[1]]!=-1)&&(board[ld[0]][ld[1]]!=i))
                {
                    if((board[ld[0]][ld[1]]<=15||(board[ld[0]][ld[1]]>=32&&board[ld[0]][ld[1]]<=35))&&validate(i,ld[0],ld[1]))
                    {
                        moves[c][0]=ld[0];
                        moves[c][1]=ld[1];
                        c++;
                    }
                    break;
                }
                else
                {
                    if(validate(i,ld[0],ld[1]))
                    {
                        moves[c][0]=ld[0];
                        moves[c][1]=ld[1];
                        c++;
                    }
                    ld[0]--;
                    ld[1]++;
                }
            }
            int rd[]={x,y};
            while((rd[0]!=8)&&(rd[1]!=8))
            {
                if((board[rd[0]][rd[1]]!=-1)&&(board[rd[0]][rd[1]]!=i))
                {
                    if((board[rd[0]][rd[1]]<=15||(board[rd[0]][rd[1]]>=32&&board[rd[0]][rd[1]]<=35))&&validate(i,rd[0],rd[1]))
                    {
                        moves[c][0]=rd[0];
                        moves[c][1]=rd[1];
                        c++;
                    }
                    break;
                }
                else
                {
                    if(validate(i,rd[0],rd[1]))
                    {
                        moves[c][0]=rd[0];
                        moves[c][1]=rd[1];
                        c++;
                    }
                    rd[0]++;
                    rd[1]++;
                }
            }
            int lu[]={x,y};
            while((lu[0]!=-1)&&(lu[1]!=-1))
            {
                if((board[lu[0]][lu[1]]!=-1)&&(board[lu[0]][lu[1]]!=i))
                {
                    if((board[lu[0]][lu[1]]<=15||(board[lu[0]][lu[1]]>=32&&board[lu[0]][lu[1]]<=35))&&validate(i,lu[0],lu[1]))
                    {
                        moves[c][0]=lu[0];
                        moves[c][1]=lu[1];
                        c++;
                    }
                    break;
                }
                else
                {
                    if(validate(i,lu[0],lu[1]))
                    {
                        moves[c][0]=lu[0];
                        moves[c][1]=lu[1];
                        c++;
                    }
                    lu[0]--;
                    lu[1]--;
                }
            }
            int ru[]={x,y};
            while((ru[0]!=8)&&(ru[1]!=-1))
            {
                if((board[ru[0]][ru[1]]!=-1)&&(board[ru[0]][ru[1]]!=i))
                {
                    if((board[ru[0]][ru[1]]<=15||(board[ru[0]][ru[1]]>=32&&board[ru[0]][ru[1]]<=35))&&validate(i,ru[0],ru[1]))
                    {
                        moves[c][0]=ru[0];
                        moves[c][1]=ru[1];
                        c++;
                    }
                    break;
                }
                else
                {
                    if(validate(i,ru[0],ru[1]))
                    {
                        moves[c][0]=ru[0];
                        moves[c][1]=ru[1];
                        c++;
                    }
                    ru[0]++;
                    ru[1]--;
                }
            }
        }
        else if(i==4)
        {
            if(x!=0)
            {
                if(y!=0)
                {
                    if(isAttacked(x-1,y-1,1)==false&&((board[x-1][y-1]==-1)||((board[x-1][y-1]>15&&board[x-1][y-1]<=31)||(board[x-1][y-1]>35&&board[x-1][y-1]<40))))
                    {
                        moves[c][0]=x-1;
                        moves[c][1]=y-1;
                        c++;
                    }
                }
                if(y!=7)
                {
                    if(isAttacked(x-1,y+1,1)==false&&((board[x-1][y+1]==-1)||((board[x-1][y+1]>15&&board[x-1][y+1]<=31)||(board[x-1][y+1]>35&&board[x-1][y+1]<40))))
                    {
                        moves[c][0]=x-1;
                        moves[c][1]=y+1;
                        c++;
                    }
                }
                if(isAttacked(x-1,y,1)==false&&((board[x-1][y]==-1)||((board[x-1][y]>15&&board[x-1][y]<=31)||(board[x-1][y]>35&&board[x-1][y]<40))))
                {
                    moves[c][0]=x-1;
                    moves[c][1]=y;
                    c++;
                }
            }
            if(x!=7)
            {
                if(y!=0)
                {
                    if(isAttacked(x+1,y-1,1)==false&&((board[x+1][y-1]==-1)||((board[x+1][y-1]>15&&board[x+1][y-1]<=31)||(board[x+1][y-1]>35&&board[x+1][y-1]<40))))
                    {
                        moves[c][0]=x+1;
                        moves[c][1]=y-1;
                        c++;
                    }
                }
                if(y!=7)
                {
                    if(isAttacked(x+1,y+1,1)==false&&((board[x+1][y+1]==-1)||((board[x+1][y+1]>15&&board[x+1][y+1]<=31)||(board[x+1][y+1]>35&&board[x+1][y+1]<40))))
                    {
                        moves[c][0]=x+1;
                        moves[c][1]=y+1;
                        c++;
                    }
                }
                if(isAttacked(x+1,y,1)==false&&((board[x+1][y]==-1)||((board[x+1][y]>15&&board[x+1][y]<=31)||(board[x+1][y]>35&&board[x+1][y]<40))))
                {
                    moves[c][0]=x+1;
                    moves[c][1]=y;
                    c++;
                }
            }
            if(y!=0)
            {
                if(isAttacked(x,y-1,1)==false&&((board[x][y-1]==-1)||((board[x][y-1]>15&&board[x][y-1]<=31)||(board[x][y-1]>35&&board[x][y-1]<40))))
                {
                    moves[c][0]=x;
                    moves[c][1]=y-1;
                    c++;
                }
            }
            if(y!=7)
            {
                if(isAttacked(x,y+1,1)==false&&((board[x][y+1]==-1)||((board[x][y+1]>15&&board[x][y+1]<=31)||(board[x][y+1]>35&&board[x][y+1]<40))))
                {
                    moves[c][0]=x;
                    moves[c][1]=y+1;
                    c++;
                }           
            }
            if(bscastle&&!isAttacked(4,0,1)&&!isAttacked(5,0,1)&&!isAttacked(6,0,1)&&board[5][0]==-1&&board[6][0]==-1&&piece[7][0]!=-1)
            {
                castmoves[k][0]=6;
                castmoves[k][1]=0;
                k++;
            }
            if(blcastle&&!isAttacked(4,0,1)&&!isAttacked(3,0,1)&&!isAttacked(2,0,1)&&board[3][0]==-1&&board[2][0]==-1&&board[1][0]==-1&&piece[0][0]!=-1)
            {
                castmoves[k][0]=2;
                castmoves[k][1]=0;
                k++;
            }
        }
        else if(i==28)
        {
            if(x!=0)
            {
                if(y!=0)
                {
                    if(isAttacked(x-1,y-1,0)==false&&((board[x-1][y-1]==-1)||((board[x-1][y-1]>-1&&board[x-1][y-1]<=15)||(board[x-1][y-1]>31&&board[x-1][y-1]<36))))
                    {
                        moves[c][0]=x-1;
                        moves[c][1]=y-1;
                        c++;
                    }
                }
                if(y!=7)
                {
                    if(isAttacked(x-1,y+1,0)==false&&((board[x-1][y+1]==-1)||((board[x-1][y+1]>-1&&board[x-1][y+1]<=15)||(board[x-1][y+1]>31&&board[x-1][y+1]<36))))
                    {
                        moves[c][0]=x-1;
                        moves[c][1]=y+1;
                        c++;
                    }
                }
                if(isAttacked(x-1,y,0)==false&&((board[x-1][y]==-1)||((board[x-1][y]>-1&&board[x-1][y]<=15)||(board[x-1][y]>31&&board[x-1][y]<36))))
                {
                    moves[c][0]=x-1;
                    moves[c][1]=y;
                    c++;
                }
            }
            if(x!=7)
            {
                if(y!=0)
                {
                    if(isAttacked(x+1,y-1,0)==false&&((board[x+1][y-1]==-1)||((board[x+1][y-1]>-1&&board[x+1][y-1]<=15)||(board[x+1][y-1]>31&&board[x+1][y-1]<36))))
                    {
                        moves[c][0]=x+1;
                        moves[c][1]=y-1;
                        c++;
                    }
                }
                if(y!=7)
                {
                    if(isAttacked(x+1,y+1,0)==false&&((board[x+1][y+1]==-1)||((board[x+1][y+1]>-1&&board[x+1][y+1]<=15)||(board[x+1][y+1]>31&&board[x+1][y+1]<36))))
                    {
                        moves[c][0]=x+1;
                        moves[c][1]=y+1;
                        c++;
                    }
                }
                if(isAttacked(x+1,y,0)==false&&((board[x+1][y]==-1)||((board[x+1][y]>-1&&board[x+1][y]<=15)||(board[x+1][y]>31&&board[x+1][y]<36))))
                {
                    moves[c][0]=x+1;
                    moves[c][1]=y;
                    c++;
                }
            }
            if(y!=0)
            {
                if(isAttacked(x,y-1,0)==false&&((board[x][y-1]==-1)||((board[x][y-1]>-1&&board[x][y-1]<=15)||(board[x][y-1]>31&&board[x][y-1]<36))))
                {
                    moves[c][0]=x;
                    moves[c][1]=y-1;
                    c++;
                }
            }
            if(y!=7)
            {
                if(isAttacked(x,y+1,0)==false&&((board[x][y+1]==-1)||((board[x][y+1]>-1&&board[x][y+1]<=15)||(board[x][y+1]>31&&board[x][y+1]<36))))
                {
                    moves[c][0]=x;
                    moves[c][1]=y+1;
                    c++;
                }           
            }
            if(wscastle&&!isAttacked(4,7,0)&&!isAttacked(5,7,0)&&!isAttacked(6,7,0)&&board[5][7]==-1&&board[6][7]==-1&&piece[31][0]!=-1)
            {
                castmoves[k][0]=6;
                castmoves[k][1]=7;
                k++;
            }
            if(wlcastle&&!isAttacked(4,7,0)&&!isAttacked(3,7,0)&&!isAttacked(2,7,0)&&board[3][7]==-1&&board[2][7]==-1&&board[1][7]==-1&&piece[24][0]!=-1)
            {
                castmoves[k][0]=2;
                castmoves[k][1]=7;
                k++;
            }
        }
    }

    boolean isAttacked(int x, int y, int c)
    {
        boolean atk = false;
        if(c==0)
        {
            int l = x-1;
            while(l>=0)
            {
                int i = board[l][y];
                if(i!=-1&&i!=28)
                {
                    if(i==0||i==3||i==7||(i>31&&i<=35))
                    {
                        atk = true;
                    }
                    break;
                }
                l--;
            }
            int r = x+1;
            while(r<8)
            {
                int i = board[r][y];
                if(i!=-1&&i!=28)
                {
                    if(i==0||i==3||i==7||(i>31&&i<=35))
                    {
                        atk = true;
                    }
                    break;
                }
                r++;
            }
            int u = y-1;
            while(u>=0)
            {
                int i = board[x][u];
                if(i!=-1&&i!=28)
                {
                    if(i==0||i==3||i==7||(i>31&&i<=35))
                    {
                        atk = true;
                    }
                    break;
                }
                u--;
            }
            int d = y+1;
            while(d<8)
            {
                int i = board[x][d];
                if(i!=-1&&i!=28)
                {
                    if(i==0||i==3||i==7||(i>31&&i<=35))
                    {
                        atk = true;
                    }
                    break;
                }
                d++;
            }
            int ld[]={x-1,y+1};
            while(ld[0]>=0&&ld[1]<8)
            {
                int i = board[ld[0]][ld[1]];
                if(i!=-1&&i!=28)
                {
                    if(i==2||i==5||i==3||(i>31&&i<=35))
                    {
                        atk = true;
                    }
                    break;
                }
                ld[0]--;
                ld[1]++;
            }
            int rd[]={x+1,y+1};
            while(rd[0]<8&&rd[1]<8)
            {
                int i = board[rd[0]][rd[1]];
                if(i!=-1&&i!=28)
                {
                    if(i==2||i==5||i==3||(i>31&&i<=35))
                    {
                        atk = true;
                    }
                    break;
                }
                rd[0]++;
                rd[1]++;
            }
            int lu[]={x-1,y-1};
            while(lu[0]>=0&&lu[1]>=0)
            {
                int i = board[lu[0]][lu[1]];
                if(i!=-1&&i!=28)
                {
                    if(i==2||i==5||i==3||(i>31&&i<=35))
                    {
                        atk = true;
                    }
                    break;
                }
                lu[0]--;
                lu[1]--;
            }
            int ru[]={x+1,y-1};
            while(ru[0]<8&&ru[1]>=0)
            {
                int i = board[ru[0]][ru[1]];
                if(i!=-1&&i!=28)
                {
                    if(i==2||i==5||i==3||(i>31&&i<=35))
                    {
                        atk = true;
                    }
                    break;
                }
                ru[0]++;
                ru[1]--;
            }
            if((x>1)&&(y>0))
            {
                if((board[x-2][y-1]==1)||(board[x-2][y-1]==6))
                {
                    atk=true;
                }
            }
            if((x>1)&&(y<7))
            {
                if((board[x-2][y+1]==1)||(board[x-2][y+1]==6))
                {
                    atk=true;
                }
            }
            if((x<6)&&(y>0))
            {
                if((board[x+2][y-1]==1)||(board[x+2][y-1]==6))
                {
                    atk=true;
                }
            }
            if((x<6)&&(y<7))
            {
                if((board[x+2][y+1]==1)||(board[x+2][y+1]==6))
                {
                    atk=true;
                }
            }
            if((x>0)&&(y>1))
            {
                if((board[x-1][y-2]==1)||(board[x-1][y-2]==6))
                {
                    atk=true;
                }
            }
            if((x>0)&&(y<6))
            {
                if((board[x-1][y+2]==1)||(board[x-1][y+2]==6))
                {
                    atk=true;
                }
            }
            if((x<7)&&(y>1))
            {
                if((board[x+1][y-2]==1)||(board[x+1][y-2]==6))
                {
                    atk=true;
                }
            }
            if((x<7)&&(y<6))
            {
                if((board[x+1][y+2]==1)||(board[x+1][y+2]==6))
                {
                    atk=true;
                }
            }
            if(x>0&&y>0)
            {
                if(board[x-1][y-1]>7&&board[x-1][y-1]<=15)
                {
                    atk = true;
                }
            }
            if(x<7&&y>0)
            {
                if(board[x+1][y-1]>7&&board[x+1][y-1]<=15)
                {
                    atk = true;
                }
            }            
            if(x!=0)
            {
                if(y!=0)
                {
                    if(board[x-1][y-1]==4)
                    {
                        atk = true;
                    }
                }
                if(y!=7)
                {
                    if(board[x-1][y+1]==4)
                    {
                        atk = true;
                    }
                }
                if(board[x-1][y]==4)
                {
                    atk = true;
                }
            }
            if(x!=7)
            {
                if(y!=0)
                {
                    if(board[x+1][y-1]==4)
                    {
                        atk = true;
                    }
                }
                if(y!=7)
                {
                    if(board[x+1][y+1]==4)
                    {
                        atk = true;
                    }
                }
                if(board[x+1][y]==4)
                {
                    atk = true;
                }
            }
            if(y!=0)
            {
                if(board[x][y-1]==4)
                {
                    atk = true;
                }
            }
            if(y!=7)
            {
                if(board[x][y+1]==4)
                {
                    atk = true;
                }    
            }
        }
        else if(c==1)
        {
            int l = x-1;
            while(l>=0)
            {
                int i = board[l][y];
                if(i!=-1&&i!=4)
                {
                    if(i==24||i==27||i==31||(i>35&&i<=39))
                    {
                        atk = true;
                    }
                    break;
                }
                l--;
            }
            int r = x+1;
            while(r<8)
            {
                int i = board[r][y];
                if(i!=-1&&i!=4)
                {
                    if(i==24||i==27||i==31||(i>35&&i<=39))
                    {
                        atk = true;
                    }
                    break;
                }
                r++;
            }
            int u = y-1;
            while(u>=0)
            {
                int i = board[x][u];
                if(i!=-1&&i!=4)
                {
                    if(i==24||i==27||i==31||(i>35&&i<=39))
                    {
                        atk = true;
                    }
                    break;
                }
                u--;
            }
            int d = y+1;
            while(d<8)
            {
                int i = board[x][d];
                if(i!=-1&&i!=4)
                {
                    if(i==24||i==27||i==31||(i>35&&i<=39))
                    {
                        atk = true;
                    }
                    break;
                }
                d++;
            }
            int ld[]={x-1,y+1};
            while(ld[0]>=0&&ld[1]<8)
            {
                int i = board[ld[0]][ld[1]];
                if(i!=-1&&i!=4)
                {
                    if(i==26||i==29||i==27||(i>35&&i<=39))
                    {
                        atk = true;
                    }
                    break;
                }
                ld[0]--;
                ld[1]++;
            }
            int rd[]={x+1,y+1};
            while(rd[0]<8&&rd[1]<8)
            {
                int i = board[rd[0]][rd[1]];
                if(i!=-1&&i!=4)
                {
                    if(i==26||i==29||i==27||(i>35&&i<=39))
                    {
                        atk = true;
                    }
                    break;
                }
                rd[0]++;
                rd[1]++;
            }
            int lu[]={x-1,y-1};
            while(lu[0]>=0&&lu[1]>=0)
            {
                int i = board[lu[0]][lu[1]];
                if(i!=-1&&i!=4)
                {
                    if(i==26||i==29||i==27||(i>35&&i<=39))
                    {
                        atk = true;
                    }
                    break;
                }
                lu[0]--;
                lu[1]--;
            }
            int ru[]={x+1,y-1};
            while(ru[0]<8&&ru[1]>=0)
            {
                int i = board[ru[0]][ru[1]];
                if(i!=-1&&i!=4)
                {
                    if(i==26||i==29||i==27||(i>35&&i<=39))
                    {
                        atk = true;
                    }
                    break;
                }
                ru[0]++;
                ru[1]--;
            }
            if((x>1)&&(y>0))
            {
                if((board[x-2][y-1]==25)||(board[x-2][y-1]==30))
                {
                    atk=true;
                }
            }
            if((x>1)&&(y<7))
            {
                if((board[x-2][y+1]==25)||(board[x-2][y+1]==30))
                {
                    atk=true;
                }
            }
            if((x<6)&&(y>0))
            {
                if((board[x+2][y-1]==25)||(board[x+2][y-1]==30))
                {
                    atk=true;
                }
            }
            if((x<6)&&(y<7))
            {
                if((board[x+2][y+1]==25)||(board[x+2][y+1]==30))
                {
                    atk=true;
                }
            }
            if((x>0)&&(y>1))
            {
                if((board[x-1][y-2]==25)||(board[x-1][y-2]==30))
                {
                    atk=true;
                }
            }
            if((x>0)&&(y<6))
            {
                if((board[x-1][y+2]==25)||(board[x-1][y+2]==30))
                {
                    atk=true;
                }
            }
            if((x<7)&&(y>1))
            {
                if((board[x+1][y-2]==25)||(board[x+1][y-2]==30))
                {
                    atk=true;
                }
            }
            if((x<7)&&(y<6))
            {
                if((board[x+1][y+2]==25)||(board[x+1][y+2]==30))
                {
                    atk=true;
                }
            }
            if(x>0&&y<7)
            {
                if(board[x-1][y+1]>15&&board[x-1][y+1]<=23)
                {
                    atk = true;
                }
            }
            if(x<7&&y<7)
            {
                if(board[x+1][y+1]>15&&board[x+1][y+1]<=23)
                {
                    atk = true;
                }
            }
            if(x!=0)
            {
                if(y!=0)
                {
                    if(board[x-1][y-1]==28)
                    {
                        atk = true;
                    }
                }
                if(y!=7)
                {
                    if(board[x-1][y+1]==28)
                    {
                        atk = true;
                    }
                }
                if(board[x-1][y]==28)
                {
                    atk = true;
                }
            }
            if(x!=7)
            {
                if(y!=0)
                {
                    if(board[x+1][y-1]==28)
                    {
                        atk = true;
                    }
                }
                if(y!=7)
                {
                    if(board[x+1][y+1]==28)
                    {
                        atk = true;
                    }
                }
                if(board[x+1][y]==28)
                {
                    atk = true;
                }
            }
            if(y!=0)
            {
                if(board[x][y-1]==28)
                {
                    atk = true;
                }
            }
            if(y!=7)
            {
                if(board[x][y+1]==28)
                {
                    atk = true;
                }    
            }
        }
        return atk;
    }

    public boolean validate(int ipiece, int dx,int dy)
    {
        boolean valid = true;
        for(int i=0;i<40;i++)
            for(int j=0;j<2;j++)
                tempp[i][j] = piece[i][j];
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                tempb[i][j] = board[i][j];
        tempbpq = bpq;
        tempwpq = wpq;
        int a = board[dx][dy];
        if((a!=-1)&&(a!=ipiece))
        {
            piece[a][0]=-1;
            piece[a][1]=-1;
        }                    
        board[piece[ipiece][0]][piece[ipiece][1]]=-1;
        piece[ipiece][0]=dx;
        piece[ipiece][1]=dy;
        board[dx][dy]=ipiece;
        if((dy==7)&&(ipiece>7)&&(ipiece<16)&&(bpq<4))
        {
            piece[ipiece][0]=-1;
            piece[ipiece][1]=-1;
            piece[32+bpq][0]=dx;
            piece[32+bpq][1]=dy;
            board[dx][dy]=32+bpq;
            bpq++;
        }
        if((dy==0)&&(ipiece>15)&&(ipiece<24)&&(wpq<4))
        {
            piece[ipiece][0]=-1;
            piece[ipiece][1]=-1;
            piece[36+wpq][0]=dx;
            piece[36+wpq][1]=dy;
            board[dx][dy]=36+wpq;
            wpq++;
        }
        if((ipiece<16||(ipiece>31&&ipiece<36))&&isAttacked(piece[4][0],piece[4][1],1))
            valid = false;
        else if(((ipiece>15&&ipiece<32)||ipiece>35)&&isAttacked(piece[28][0],piece[28][1],0))
            valid  = false;
        for(int i=0;i<40;i++)
            for(int j=0;j<2;j++)
                piece[i][j] = tempp[i][j];
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                board[i][j] = tempb[i][j];
        bpq = tempbpq;
        wpq = tempwpq;
        return valid;
    }

    public void checkMate()
    {
        noofmoves = 0;
        for(int i=0;i<36;i++)
        {
            if((i>15&&i<32)||(piece[i][0]==-1&&piece[i][0]==-1))
                continue;            
            generateMoves(piece[i][0],piece[i][1]);
            noofmoves = noofmoves+c;
            if(noofmoves>0)
                break;
        }
        if(noofmoves==0&&bcheck&&turn==0)
        {
            bmate = true;
        }
        else if(noofmoves==0&&turn==0)
        {
            bstale = true;
        }
        noofmoves = 0;
        for(int i=16;i<40;i++)
        {
            if((i>31&&i<36)||(piece[i][0]==-1&&piece[i][0]==-1))
                continue;
            generateMoves(piece[i][0],piece[i][1]);
            noofmoves = noofmoves+c;
            if(noofmoves>0)
                break;
        }
        if(noofmoves==0&&wcheck&&turn==1)
        {
            wmate = true;
        }
        else if(noofmoves==0&&turn==1)
        {
            wstale = true;
        }
    }
    
    public void checkDraw()
    {
        draw = true;
        boolean minorFound = false;
        for(int i=0;i<40;i++)
        {            
            if(i==4||i==28)
            continue;
            if(piece[i][0]!=-1)
            {
                if(i==1||i==2||i==5||i==6||i==25||i==26||i==29||i==30)
                {
                    if(minorFound)
                    draw = false;
                    else
                    minorFound = true;
                }
                else
                draw = false;
            }
        }
    }

    public void mousePressed(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY()-30;
        int inx = (x-50)/square;
        int iny = (y-50)/square;
        if(!(bmate||wmate||wstale||bstale||draw)&&(inx>-1)&&(iny>-1)&&(inx<8)&&(iny<8))
        {
            if(selected == false)
            {
                int i = board[inx][iny];
                if(((turn==0)&&((i<=15&&i>-1)||(i>31&&i<36)))||((turn==1)&&((i>15&&i<32)||(i>35&&i<40))))
                { 
                    if(i!=-1)
                    {                
                        focus = i;
                        selected = true;
                        generateMoves(inx,iny);
                    }
                }
            }
            else
            {
                for(int j=0;j<c;j++)
                {
                    if((moves[j][0]==inx)&&(moves[j][1]==iny)&&!(inx==piece[focus][0]&&iny==piece[focus][1]))
                    {
                        int i = board[inx][iny];
                        if((i!=-1)&&(i!=focus))
                        {
                            piece[i][0]=-1;
                            piece[i][1]=-1;
                        }                    
                        board[piece[focus][0]][piece[focus][1]]=-1;
                        piece[focus][0]=inx;
                        piece[focus][1]=iny;
                        board[inx][iny]=focus;
                        turn = 1-turn;
                        if((iny==7)&&(focus>7)&&(focus<16)&&(bpq<4))
                        {
                            piece[focus][0]=-1;
                            piece[focus][1]=-1;
                            piece[32+bpq][0]=inx;
                            piece[32+bpq][1]=iny;
                            board[inx][iny]=32+bpq;
                            bpq++;
                        }
                        if((iny==0)&&(focus>15)&&(focus<24)&&(wpq<4))
                        {
                            piece[focus][0]=-1;
                            piece[focus][1]=-1;
                            piece[36+wpq][0]=inx;
                            piece[36+wpq][1]=iny;
                            board[inx][iny]=36+wpq;
                            wpq++;
                        }
                        if(focus==0)
                        {
                            blcastle = false;
                        }
                        if(focus==7)
                        {
                            bscastle = false;
                        }
                        if(focus==24)
                        {
                            wlcastle = false;
                        }
                        if(focus==31)
                        {
                            wscastle = false;
                        }
                        if(focus==4)
                        {
                            blcastle = false;
                            bscastle = false;
                        }
                        if(focus==28)
                        {
                            wlcastle = false;
                            wscastle = false;
                        }
                    }
                }
                for(int j=0;j<k;j++)
                {
                    if(castmoves[j][0]==inx&&castmoves[j][1]==iny)
                    {
                        if(castmoves[j][0]==2&&castmoves[j][1]==0)
                        {
                            board[2][0]=4;
                            piece[4][0]=2;
                            piece[4][1]=0;
                            board[4][0]=-1;
                            board[3][0]=0;
                            piece[0][0]=3;
                            piece[0][1]=0;
                            board[0][0]=-1;                            
                            turn = 1-turn;
                            bscastle=blcastle=false;
                        }
                        if(castmoves[j][0]==6&&castmoves[j][1]==0)
                        {
                            board[6][0]=4;
                            piece[4][0]=6;
                            piece[4][1]=0;
                            board[4][0]=-1;
                            board[5][0]=7;
                            piece[7][0]=5;
                            piece[7][1]=0;
                            board[7][0]=-1;
                            turn = 1-turn;
                            bscastle=blcastle=false;
                        }
                        if(castmoves[j][0]==2&&castmoves[j][1]==7)
                        {
                            board[2][7]=28;
                            piece[28][0]=2;
                            piece[28][1]=7;
                            board[4][7]=-1;
                            board[3][7]=24;
                            piece[24][0]=3;
                            piece[24][1]=7;
                            board[0][7]=-1;
                            turn = 1-turn;
                            wscastle=wlcastle=false;
                        }
                        if(castmoves[j][0]==6&&castmoves[j][1]==7)
                        {
                            board[6][7]=28;
                            piece[28][0]=6;
                            piece[28][1]=7;
                            board[4][7]=-1;
                            board[5][7]=31;
                            piece[31][0]=5;
                            piece[31][1]=7;
                            board[7][7]=-1;
                            turn = 1-turn;
                            wscastle=wlcastle=false;
                        }
                    }
                }
                if(isAttacked(piece[4][0],piece[4][1],1))
                {
                    bcheck = true;
                }
                else 
                {
                    bcheck = false;
                }
                if(isAttacked(piece[28][0],piece[28][1],0))
                {
                    wcheck = true;
                }
                else
                {
                    wcheck = false;
                }
                checkMate();
                checkDraw();
                selected = false;
            }  
            repaint();
        }
        else if((bmate||wmate||wstale||bstale||draw))
        {
            if(preview==true)
            {
                preview = false;                
                repaint();
            }
            else if(x>=80+square*2+(square-60)*2&&x<=260+square*2+(square-60)*2&&y>=100+square*3+square-60&&y<=125+square*3+square-60)
            {
                preview = true;                
                repaint();
            }
            else if(x>=80+square*2+(square-60)*2&&x<=260+square*2+(square-60)*2&&y>=130+square*3+square-60&&y<=155+square*3+square-60)
            {
                reset();
                repaint();
            }
        }
    }

    public void mouseDragged(MouseEvent e) {}

    public void mouseMoved(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) {}

    public void componentHidden(ComponentEvent e) {} 

    public void componentMoved(ComponentEvent e) {} 

    public void componentResized(ComponentEvent e) {} 

    public void componentShown(ComponentEvent e) {}
}
