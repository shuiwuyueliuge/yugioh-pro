import { Component, OnInit } from '@angular/core';
import { Card } from './card';
import { CardService } from './card.service';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.scss']
})
export class CardComponent implements OnInit {

   cards: Card[] = [];

  constructor(private cardService: CardService) { }

  ngOnInit(): void {
    this.initCard();
  }

  private initCard(): void {
    this.cardService.getCard().then(data => {
      this.cards = data.data;
    });
  }
}
