export interface WebSocketMsg<T> {
    code?: number,
    data?: T,
    msg?: string,
    source?: WebSocketSource
}

export interface WebSocketSource {
    channelId?: string,
    subscribe: string
}