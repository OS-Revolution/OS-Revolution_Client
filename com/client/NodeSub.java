package com.client;

class NodeSub extends Node {

    final void unlinkSub() {
        if (nextNodeSub == null) {
        } else {
            nextNodeSub.prevNodeSub = prevNodeSub;
            prevNodeSub.nextNodeSub = nextNodeSub;
            prevNodeSub = null;
            nextNodeSub = null;
        }
    }

    NodeSub() {
    }

    NodeSub prevNodeSub;
    NodeSub nextNodeSub;
}
