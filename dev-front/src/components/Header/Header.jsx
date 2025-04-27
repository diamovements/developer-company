import React from 'react';


const Header = () => {
    return (
        <header>
            <div className="scene">
                <div className="layer" style={{ backgroundImage: 'url(/../../images/background.jpg)' }}></div>
                <div className="layer" style={{ backgroundImage: 'url(/../../images/layer3.png)' }}></div>
                <div className="layer" style={{ backgroundImage: 'url(/../../images/layer2.png)' }}>
                    <div className="layer__content">
                        <h1>MOSCOW BUILD</h1>
                    </div>
                </div>
                <div className="layer" style={{ backgroundImage: 'url(/../../images/layer1.png)' }}></div>
                <div className="layer">
                    <div className="layer__end">
                        <h3>Воплощаем ваши мечты в реальность</h3>
                    </div>
                </div>
            </div>
        </header>
    );
};

export default Header;