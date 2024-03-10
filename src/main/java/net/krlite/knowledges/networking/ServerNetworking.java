package net.krlite.knowledges.networking;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.krlite.knowledges.KnowledgesClient;
import net.krlite.knowledges.impl.representable.KnowledgesBlockRepresentable;
import net.krlite.knowledges.impl.representable.KnowledgesEntityRepresentable;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class ServerNetworking implements KnowledgesNetworking {
    @Override
    public void register() {
        ServerPlayNetworking.registerGlobalReceiver(PACKET_PEEK_BLOCK, new PeekBlock());
        ServerPlayNetworking.registerGlobalReceiver(PACKET_PEEK_ENTITY, new PeekEntity());
        ServerPlayConnectionEvents.JOIN.register(new PlayerJoin());
    }

    public static class PeekBlock implements ServerPlayNetworking.PlayChannelHandler {
        @Override
        public void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
            KnowledgesBlockRepresentable.onRequest(buf, player, server::execute, compound -> {
                PacketByteBuf data = PacketByteBufs.create();
                data.writeNbt(compound);
                responseSender.sendPacket(PACKET_RECEIVE_DATA, data);
            });
        }
    }

    public static class PeekEntity implements ServerPlayNetworking.PlayChannelHandler {
        @Override
        public void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
            KnowledgesEntityRepresentable.onRequest(buf, player, server::execute, compound -> {
                PacketByteBuf data = PacketByteBufs.create();
                data.writeNbt(compound);
                responseSender.sendPacket(PACKET_RECEIVE_DATA, data);
            });
        }
    }

    public static class PlayerJoin implements ServerPlayConnectionEvents.Join {
        @Override
        public void onPlayReady(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
            ServerPlayerEntity player = handler.getPlayer();
            ServerPlayNetworking.send(player, PACKET_SERVER_PING, PacketByteBufs.create());
            KnowledgesClient.CACHE_USERNAME.put(player);
        }
    }
}
