import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.gibbet.minimalgame.MapCollider;
import com.gibbet.minimalgame.Models.TileCollision;

import com.badlogic.gdx.math.Vector2;


public class BoundsDetector {
	
	static String inputDir = "C:\\Users\\Matt\\workspace\\games\\ludumdare\\assets\\tiles\\input";
	
	public static void main(String[] args){
		File dir = new File(inputDir);
		MapCollider mc = new MapCollider();
		for (File child : dir.listFiles()) {
			System.out.println(child);
			if (child.getName().endsWith(".png")){
				try {
					BufferedImage reader = ImageIO.read(child);
					TileCollision tc = new TileCollision();
					for (int x=0; x < reader.getWidth(); x++){
						for (int y=0;y < reader.getHeight(); y++){
							int clr =  reader.getRGB(x,y);
							//we only want pixels that are white for now
							
							int  red   = (clr & 0x00ff0000) >> 16;
							int  green = (clr & 0x0000ff00) >> 8;
							int  blue  =  clr & 0x000000ff;
							if (red == 255 && green == 255 & blue==255){
								tc.addCollisionPoint(new Vector2(x,y));
							}
							System.out.println(x+" - "+y+" - "+red + "-"+green+"-"+blue);
						}
					}
					String[] tmp1 = child.getName().split(".png");
					System.out.println(tmp1[0]);
					String[] tmp2 = tmp1[0].split("_");
					mc.addCollision(Integer.valueOf(tmp2[tmp2.length-1]),tc);
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			mc.saveTileCollisions("C:\\Users\\Matt\\workspace\\games\\ludumdare\\assets\\tiles\\input\\alldata.json");
		}
	}
}
