package main

import (
	"time"
	"fmt"
)

func showAndSwitchState(ch chan<- *Interaction, interact *Interaction, directionType string) {
	oldState := *interact
	var duration time.Duration
	switch interact.state {
	case "RED":
		interact.state = "GREEN"
		duration = 10 * time.Second
	case "GREEN":
		interact.state = "YELLOW"
		duration = 5 * time.Second
	case "YELLOW":
		interact.state = "RED"
		duration = 0 * time.Second
		interact.directionNSToRun = !interact.directionNSToRun
	}
	t := time.Now()
	fmt.Printf("%d-%02d-%02d %02d:%02d:%02d   Changing %v from %v to %v\n",
		t.Year(), t.Month(), t.Day(),
		t.Hour(), t.Minute(), t.Second(),
		directionType, oldState.state, interact.state)
	//fmt.Println("Changing", directionType, "from",oldState.state,"to",interact.state)
	time.Sleep(duration)
	ch <- interact
}

func traffic_direction(directionType string, ch chan *Interaction) {
	var north_or_south bool
	switch directionType {
	case "N", "S":
		north_or_south = true
	case "E", "W":
		north_or_south = false
	}
	for {
		interact := <-ch
		if north_or_south == interact.directionNSToRun {
			showAndSwitchState(ch, interact, directionType)
		} else {
			ch <- interact
		}
	}
}

type Interaction struct {
	directionNSToRun bool
	state string
}

func main() {
	interact := make(chan *Interaction, 2)
	go traffic_direction("N", interact)
	go traffic_direction("S", interact)
	go traffic_direction("E", interact)
	go traffic_direction("W", interact)
	interact <- &Interaction{directionNSToRun: true, state: "RED"}
	interact <- &Interaction{directionNSToRun: true, state: "RED"}
	time.Sleep(1 * time.Minute)
	<-interact
	<-interact
}
