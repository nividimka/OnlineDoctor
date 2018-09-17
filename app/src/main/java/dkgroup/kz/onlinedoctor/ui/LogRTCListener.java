package dkgroup.kz.onlinedoctor.ui;

import android.util.Log;

import org.webrtc.MediaStream;

import me.kevingleason.pnwebrtc.PnPeer;
import me.kevingleason.pnwebrtc.PnRTCListener;
import me.kevingleason.pnwebrtc.PnRTCMessage;

/**
 * <p>Created by GleasonK on 7/23/15.</p>
 */
public class LogRTCListener extends PnRTCListener {
    @Override
    public void onCallReady(String callId) {
        Log.e("RTCListener", "OnCallReady - " + callId);
    }

    @Override
    public void onConnected(String userId) {
        Log.e("RTCListener", "OnConnected - " + userId);
    }

    @Override
    public void onPeerStatusChanged(PnPeer peer) {
        Log.e("RTCListener", "OnPeerStatusChanged - " + peer.toString());
    }

    @Override
    public void onPeerConnectionClosed(PnPeer peer) {
        Log.e("RTCListener", "OnPeerConnectionClosed - " + peer.toString());
    }

    @Override
    public void onLocalStream(MediaStream localStream) {
        Log.e("RTCListener", "OnLocalStream - " + localStream.toString());
    }

    @Override
    public void onAddRemoteStream(MediaStream remoteStream, PnPeer peer) {
        Log.e("RTCListener", "OnAddRemoteStream - " + peer.toString());
    }

    @Override
    public void onRemoveRemoteStream(MediaStream remoteStream, PnPeer peer) {
        Log.e("RTCListener", "OnRemoveRemoteStream - " + peer.toString());
    }

    @Override
    public void onMessage(PnPeer peer, Object message) {
        Log.e("RTCListener", "OnMessage - " + message.toString());
    }

    @Override
    public void onDebug(PnRTCMessage message) {
        Log.e("RTCListener", "OnDebug - " + message.getMessage());
    }
}
