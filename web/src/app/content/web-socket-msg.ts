export interface WebSocketMsg<T> {
    channelId?: string,
    code?: number,
    data?: T,
    msg?: string,
    subscribe: string;
}