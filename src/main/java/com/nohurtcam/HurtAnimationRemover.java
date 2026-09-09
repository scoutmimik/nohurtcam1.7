package wtf.boomy.mods.har;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
   modid = "hurtanimationremover-port",
   version = "1.0",
   acceptedMinecraftVersions = "[1.8.8,1.8.9]",
   clientSideOnly = true
)
public class HurtAnimationRemover {
   public static final String MOD_ID = "hurtanimationremover-port";
   public static final String VERSION = "1.0";

   @EventHandler
   public void preInit(FMLPreInitializationEvent event) {
      ModMetadata data = event.getModMetadata();
      data.name = "HurtAnimationRemover";
      data.modId = "hurtanimationremover-port";
      data.description = "Disables the screen shake from being hurt";
      data.authorList.add("boomboompower");
      data.version = "1.0";
   }
}
