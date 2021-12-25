package me.aristhena.client.module.modules.auto;

import java.util.Random;

import me.aristhena.client.module.Module;
import me.aristhena.client.module.Module.Mod;
import me.aristhena.event.EventTarget;
import me.aristhena.event.events.UpdateEvent;
import me.aristhena.utils.ClientUtils;
import me.aristhena.utils.Timer;

@Mod(displayName = "/fireball")
public class fireball extends Module
{
    private Timer time;
    private String[] phraseList;
    private int lastUsed;
    
    public fireball() {
        this.time = new Timer();
        this.phraseList = new String[] { "/fireball", "/fireball", "/fireball" };
    }
    
    @EventTarget
    private void onPreUpdate(final UpdateEvent E) {
        if (this.time.delay((float)this.randomDelay())) {
            ClientUtils.player().sendChatMessage(this.randomPhrase());
            this.time.reset();
        }
    }
    
    private String randomPhrase() {
        Random rand;
        int randInt;
        for (rand = new Random(), randInt = rand.nextInt(this.phraseList.length); this.lastUsed == randInt; randInt = rand.nextInt(this.phraseList.length)) {}
        this.lastUsed = randInt;
        return this.phraseList[randInt];
    }
    
    private int randomDelay() {
        final Random randy = new Random();
        final int randyInt = randy.nextInt(1200) + 1200;
        return randyInt;
    }
}
