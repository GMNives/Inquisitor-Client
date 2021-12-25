package me.aristhena.client.module.modules.combat.aura;

import java.util.*;
import net.minecraft.entity.*;

class Breakerswitch$1 implements Comparator<EntityLivingBase>
{
    public Breakerswitch$1(final BreakerSwitch breakerSwitch) {
    }
    
    @Override
    public int compare(final EntityLivingBase target1, final EntityLivingBase target2) {
        return Math.round(target2.getHealth() - target1.getHealth());
    }
}
