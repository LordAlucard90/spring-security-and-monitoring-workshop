CREATE TABLE `posts` (
    `id`        INTEGER         NOT NULL,
    `title`     VARCHAR(64)     NOT NULL,
    `content`   VARCHAR(1024)   NOT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO `posts`
(`id`, `title`, `content`)
VALUES
(1,'First','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras non urna erat.'),
(2,'Second','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras non urna erat. Vestibulum in tempor est. Maecenas ultricies fringilla lacinia. Sed ac blandit arcu, eget pretium lorem. Nullam vestibulum fermentum odio. Etiam ipsum velit, placerat at vulputate eget, maximus et justo. Vestibulum gravida orci purus, quis malesuada urna porta vel.'),
(3,'Third','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras non urna erat. Vestibulum in tempor est. Maecenas ultricies fringilla lacinia. Sed ac blandit arcu, eget pretium lorem. Nullam vestibulum fermentum odio. Etiam ipsum velit, placerat at vulputate eget, maximus et justo. Vestibulum gravida orci purus, quis malesuada urna porta vel. Donec dignissim massa urna, sed consectetur ipsum efficitur at. In vehicula purus nulla, at tempor leo cursus tincidunt. Aliquam condimentum tortor eget massa tempus auctor.'),
(4,'Fourth','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras non urna erat. Vestibulum in tempor est. Maecenas ultricies fringilla lacinia. Sed ac blandit arcu, eget pretium lorem. Nullam vestibulum fermentum odio. Etiam ipsum velit, placerat at vulputate eget, maximus et justo. Vestibulum gravida orci purus, quis malesuada urna porta vel. Donec dignissim massa urna, sed consectetur ipsum efficitur at. In vehicula purus nulla, at tempor leo cursus tincidunt. Aliquam condimentum tortor eget massa tempus auctor. Fusce id dui faucibus, rutrum tortor id, molestie lorem. Nunc finibus malesuada posuere.');
