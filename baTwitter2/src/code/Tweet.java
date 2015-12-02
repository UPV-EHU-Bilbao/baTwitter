package code;

public class Tweet {

	private long id;
	private String edukia;
	private String idazlea;
	private boolean rt;
	private boolean	fav;
	private int rtKop;
	private int favKop;
	
	
	public Tweet(String edukia,String idazlea, boolean rt,boolean fav, int rtKop,int favKop,long id){
		this.edukia=edukia;
		this.idazlea=idazlea;
		this.rt=rt;
		this.fav=fav;
		this.rtKop=rtKop;
		this.favKop=favKop;
		this.id=id;
	}
	
}
