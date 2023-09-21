import React, {useEffect, useState} from 'react';
import axios from 'axios';
import './App.css';
import SummorLog from "./components/summorLog";
import Profile from "./pages/profile";

function App() {

    const [summortext, setSummorText] = useState('')

    const inputSummorHandler = (e) => {
        if(e.key === 'Enter'){
            alert(`입력된 텍스트 ${summortext}`);
        }
    }
    return (
        <div className="main">
            <p className="helloThisWebsite">Hello This website riotAPI Test website</p>
            <input
                type="text"
                spellCheck={false}
                className="main-serchbox"
                value={summortext}
                onChange={(e) => setSummorText(e.target.value)}
                onKeyDown={inputSummorHandler}
            />
            <p>{summortext}</p>
            <SummorLog></SummorLog>
        </div>
    );
}

export default App;
