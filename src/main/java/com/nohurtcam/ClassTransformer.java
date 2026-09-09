package com.nohurtcam;

import net.minecraft.launchwrapper.IClassTransformer;
import cpw.mods.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnNode;

public class ClassTransformer implements IClassTransformer {
   public byte[] transform(String name, String transformedName, byte[] bytes) {
      if (transformedName.equalsIgnoreCase("net.minecraft.client.renderer.EntityRenderer")) {
         ClassReader reader = new ClassReader(bytes);
         ClassNode classNode = new ClassNode();
         reader.accept(classNode, 0);
         
         classNode.methods.forEach((method) -> {
            String methodName = FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(classNode.name, method.name, method.desc);
            
            if (methodName.equals("hurtCameraEffect") || methodName.equals("func_78482_e") || method.name.equals("func_78482_e")) {
               System.out.println("Patching hurt camera effect: " + method.name);
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
