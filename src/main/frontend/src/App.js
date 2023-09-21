import React, {useEffect, useState} from 'react';
import axios from 'axios';
import './App.css';
import SummorLog from "./components/summorLog";
import Profile from "./pages/profile";
import {error} from "bfj/src/events";

function App() {

    const [summordata, setSummorData] = useState('')
    const [summonername, setSummonerName] = useState('')

    const inputSummorHandler = (e) => {
        if(e.key === 'Enter'){
            axios.get(`/api/Summoner/${summonername}`)
                .then(response => setSummorData(response.data))
                .catch(error => console.log(error))
            alert(`전송 완료 : ${summonername}`);
        }
    }
    return (
        <div className="main">
            <p className="helloThisWebsite">Hello This website riotAPI Test website</p>
            <input
                type="text"
                spellCheck={false}
                className="main-serchbox"
                value={summonername}
                onChange={(e) => setSummonerName(e.target.value)}
                onKeyDown={inputSummorHandler}
            />
            <p>{summonername}</p>
            <SummorLog></SummorLog>
        </div>
    );
}

export default App;
