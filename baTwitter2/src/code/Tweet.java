package code;

/**
 * Tweet klase laguntzailea
 * tweet baten egitura du.
 * @author bingen
 *
 */
public class Tweet {

	private long id;
	private String edukia;
	private String idazlea;
	private boolean rt;
	private boolean	fav;
	private int rtKop;
	private int favKop;
	private String url;
	
	
	public Tweet(String edukia,String idazlea, boolean rt,boolean fav, int rtKop,int favKop,long id,String url){
		this.edukia=edukia;
		this.idazlea=idazlea;
		this.rt=rt;
		this.fav=fav;
		this.rtKop=rtKop;
		this.favKop=favKop;
		this.id=id;
		this.url=url;
	}
	
	public String getTextua(){
		return edukia;
	}
	
	public boolean getFav(){
		return fav;
	}
	
	public boolean getRT(){return rt;}
	public long getId(){return id;}
	public String getIdazlea(){return idazlea;}
	public int getRtKop(){return rtKop;}
	public int getFavKop(){return favKop;}
	public String getUrl(){return url;}

	
		public Object getBalioa(int i){
			switch(i){
				case 0:{	return idazlea;	}
				case 1:{	return edukia;}
				case 2:{	return rtKop;}
				case 3:{	return rt;}
				case 4:{	return fav;}
				default:{ return null;}
			}
		}
	
}
