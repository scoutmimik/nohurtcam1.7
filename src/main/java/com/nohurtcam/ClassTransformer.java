package wtf.boomy.mods.har;

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
         reader.accept(classNode, 4);
         classNode.methods.forEach((method) -> {
            String methodName = FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(classNode.name, method.name, method.desc);
            if (!methodName.equals("hurtCameraEffect") && !methodName.equals("func_78482_e")) {
               System.out.println(method.name + " -> " + methodName);
            } else {
               System.out.println("Patching " + methodName);
               method.instructions.insertBefore(method.instructions.getFirst(), new InsnNode(177));
               System.out.println("Patched " + methodName);
            }

         });
         ClassWriter writer = new ClassWriter(2);
         classNode.accept(writer);
         return writer.toByteArray();
      } else {
         return bytes;
      }
   }
}
