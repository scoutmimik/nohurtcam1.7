package com.nohurtcam;

import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnNode;

public class ClassTransformer implements IClassTransformer {
   public byte[] transform(String name, String transformedName, byte[] bytes) {
      if (transformedName.equalsIgnoreCase("net.minecraft.client.renderer.EntityRenderer")) {
         ClassReader reader = new ClassReader(bytes);
         ClassNode classNode = new ClassNode();
         reader.accept(classNode, 0); // Použitie 0 namiesto 4 (SKIP_CODE) aby sme načítali inštrukcie správne
         
         classNode.methods.forEach((method) -> {
            String methodName = FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(classNode.name, method.name, method.desc);
            
            // Overujeme remapped názov, SRG názov, ale aj pôvodný mcp/srg názov metódy
            if (methodName.equals("hurtCameraEffect") || methodName.equals("func_78482_e") || method.name.equals("func_78482_e")) {
               System.out.println("Patching hurt camera effect: " + method.name);
               // Vložíme RETURN (177) na samý začiatok metódy, čím sa hneď ukončí a kamera sa zatrasie
               method.instructions.insertBefore(method.instructions.getFirst(), new InsnNode(177));
               System.out.println("Successfully patched hurt camera effect.");
            }
         });
         
         ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
         classNode.accept(writer);
         return writer.toByteArray();
      } else {
         return bytes;
      }
   }
}
