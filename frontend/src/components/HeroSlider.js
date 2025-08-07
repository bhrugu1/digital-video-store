// Hero Slider component - Large image carousel for homepage banner
import React from 'react';
import Slider from 'react-slick';

// Featured movies/shows to display in the hero banner
const banners = [
  {
    title: "Avengers: Endgame",
    image: "https://image.tmdb.org/t/p/original/ulzhLuWrPK07P1YkdWQLZnQh1JL.jpg"
  },
  {
    title: "Stranger Things",
    image: "https://image.tmdb.org/t/p/original/x2LSRK2Cm7MZhjluni1msVJ3wDF.jpg"
  },
  {
    title: "The Matrix",
    image: "https://image.tmdb.org/t/p/original/f89U3ADr1oiB1s9GkdPOEpXUk5H.jpg"
  }
];

const HeroSlider = () => {
  // Slider configuration settings
  const settings = {
    dots: true,           // Show navigation dots
    infinite: true,       // Loop back to first slide after last
    autoplay: true,       // Automatically advance slides
    speed: 500,           // Transition speed in milliseconds
    slidesToShow: 1,      // Show one slide at a time
    slidesToScroll: 1     // Scroll one slide at a time
  };

  return (
    <div className="hero-slider">
      <Slider {...settings}>
        {/* Create a slide for each banner */}
        {banners.map((banner, index) => (
          <div key={index}>
            <img 
              src={banner.image} 
              alt={banner.title} 
              className="hero-img"
              onError={(e) => {
                // Show placeholder if image fails to load
                e.target.src = 'https://via.placeholder.com/1200x450/1c1c1c/ffffff?text=No+Image';
              }}
            />
            <h2 className="hero-title">{banner.title}</h2>
          </div>
        ))}
      </Slider>
    </div>
  );
};

export default HeroSlider;
