package com.nohurtcam;

import java.util.Map;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;

@MCVersion("1.7.10")
@TransformerExclusions({"wtf.boomy.mods"})
public class FMLLoadingPlugin implements IFMLLoadingPlugin {
   public String[] getASMTransformerClass() {
      return new String[]{ClassTransformer.class.getName()};
   }

   public String getModContainerClass() {
      return null;
   }

   public String getSetupClass() {
      return null;
   }

   public void injectData(Map<String, Object> data) {
   }

   public String getAccessTransformerClass() {
      return null;
   }
}
