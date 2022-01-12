package it.md_4.troy.modules.combat;

import it.md_4.troy.command.impl.FastKillaura;
import it.md_4.troy.modules.Module;
import it.md_4.troy.modules.events.Event;
import it.md_4.troy.modules.events.listeners.EventMotion;
import it.md_4.troy.ui.notification.Notification;
import it.md_4.troy.ui.notification.NotificationManager;
import it.md_4.troy.ui.notification.NotificationType;
import it.md_4.troy.ui.utils.Timer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C02PacketUseEntity;
import org.lwjgl.input.Keyboard;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class KillAura extends Module {

    public Timer timer = new Timer();

    public KillAura() {
        super("KillAura", Keyboard.KEY_K, Category.CPMO);
    }

    public void onEnable() {
        NotificationManager.show(new Notification(NotificationType.INFO, "KillAura", "KillAura Enabled", 2));
    }

    public void onDisable() {
        NotificationManager.show(new Notification(NotificationType.INFO, "KillAura", "KillAura Disabled", 2));
    }

    @Override
    public void onUpdate() {

    }

    public void onEvent(Event e) {
        if(e instanceof EventMotion){
            if(e.isPre()){

                EventMotion event = (EventMotion)e;

                List<Entity> targets = mc.theWorld.loadedEntityList.stream().filter(EntityLivingBase.class::isInstance).collect(Collectors.toList());

                targets = targets.stream().filter(entity -> entity.getDistanceToEntity(mc.thePlayer) < 4 && entity != mc.thePlayer && !entity.isDead).collect(Collectors.toList());

                targets.sort(Comparator.comparingDouble(entity -> ((EntityLivingBase)entity).getDistanceToEntity(mc.thePlayer)));

                targets = targets.stream().filter(EntityPlayer.class::isInstance).collect(Collectors.toList());

                if(!targets.isEmpty()){
                    EntityLivingBase target = (EntityLivingBase) targets.get(0);



                    if(FastKillaura.FastKillaura.contains("true")){
                        mc.thePlayer.rotationYaw = (getRotations(target)[0]);
                        mc.thePlayer.rotationPitch = (getRotations(target)[1]);
                    } else if(FastKillaura.FastKillaura.contains("false")){
                        event.setYaw(getRotations(target)[0]);
                        event.setPitch(getRotations(target)[1]);
                    }

                    if(timer.hasTimeElapsed(1000 / 10, true)){
                        mc.thePlayer.swingItem();
                        mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(target, C02PacketUseEntity.Action.ATTACK));
                    }
                }
            }
        }
    }

    public float[] getRotations(Entity e){

        double deltaX = e.posX + (e.posX - e.lastTickPosX) - mc.thePlayer.posX,
                deltaY = e.posY - 3.5 + e.getEyeHeight() - mc.thePlayer.posY + mc.thePlayer.getEyeHeight(),
                deltaZ = e.posZ + (e.posZ - e.lastTickPosZ) - mc.thePlayer.posZ,
                distance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaZ, 2));

        float yaw = (float) Math.toDegrees(-Math.atan(deltaX / deltaZ)),
                pitch = (float) -Math.toDegrees(Math.atan(deltaY / distance));

        if(deltaX < 0 && deltaZ < 0){
            yaw = (float) (90 + Math.toDegrees(Math.atan(deltaZ / deltaX)));
        } else if (deltaX > 0 && deltaZ < 0){
            yaw = (float) (-90 + Math.toDegrees(Math.atan(deltaZ / deltaX)));
        }
        return new float[] { yaw , pitch};
    }
}
