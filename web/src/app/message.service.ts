import { Injectable } from '@angular/core';
import { MessageService } from 'primeng/api';

@Injectable({
  providedIn: 'root'
})
export class ToastService {

  constructor(private messageService: MessageService) {}

  public addMsg(severity: string, summary: string, detail: string): void {
    this.messageService.add({ 
      severity: severity, 
      summary: summary, 
      detail: detail, 
      closable: false 
    });
  }
}
