import { Component } from '@angular/core';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent {

  title = 'angular-project';
  content = [
    {
      src: "https://i.imgur.com/RsxaCyY.png",
      name: "TREE",
      text: "Lorem ipsum dolor sit amet consectetur adipisicing elit. Quas cum cumque minus iste veritatis provident at."
    },
    {
      src:"https://i.imgur.com/ZIMTKpj.png",
      name: "GLOBE",
      text: "Lorem ipsum dolor sit amet consectetur adipisicing elit. Quas cum cumque minus iste veritatis provident at."
    },
    {
      src:"https://i.imgur.com/vG8C5v5.png",
      name: "CANDY",
      text: "Lorem ipsum dolor sit amet consectetur adipisicing elit. Quas cum cumque minus iste veritatis provident at."
    },
    {
      src:"https://i.imgur.com/WRA4BwS.png",
      name: "SNOWMAN",
      text: "Lorem ipsum dolor sit amet consectetur adipisicing elit. Quas cum cumque minus iste veritatis provident at."
    },
    {
      src:"https://i.imgur.com/JMDcVT1.png",
      name: "GIFT",
      text: "Lorem ipsum dolor sit amet consectetur adipisicing elit. Quas cum cumque minus iste veritatis provident at."
    }
  ]

}
