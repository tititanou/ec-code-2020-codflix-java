-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jul 12, 2020 at 04:04 PM
-- Server version: 5.7.24
-- PHP Version: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ec code`
--

-- --------------------------------------------------------

--
-- Table structure for table `genre`
--

CREATE TABLE `genre` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `genre`
--

INSERT INTO `genre` (`id`, `name`) VALUES
(1, 'Action'),
(2, 'Horreur'),
(3, 'Science-Fiction');

-- --------------------------------------------------------

--
-- Table structure for table `history`
--

CREATE TABLE `history` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `media_id` int(11) NOT NULL,
  `start_date` datetime NOT NULL,
  `finish_date` datetime DEFAULT NULL,
  `watch_duration` int(11) NOT NULL DEFAULT '0' COMMENT 'in seconds'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `media`
--

CREATE TABLE `media` (
  `id` int(11) NOT NULL,
  `genre_id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `type` varchar(20) NOT NULL,
  `status` varchar(20) NOT NULL,
  `release_date` date NOT NULL,
  `summary` longtext NOT NULL,
  `trailer_url` varchar(100) NOT NULL,
  `poster_url` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `media`
--

INSERT INTO `media` (`id`, `genre_id`, `title`, `type`, `status`, `release_date`, `summary`, `trailer_url`, `poster_url`) VALUES
(1, 2, 'Bodyguard', 'Film', 'publié', '1992-12-09', 'Frank Farmer, ancien agent des services secrets, est un garde du corps émérite qui a mis ses talents a la disposition de deux présidents et de nombreux financiers et politiciens de réputation internationale. Un jour l’imprésario Bill Devaney lui propose un contrat avantageux pour assurer la protection de sa cliente Rachel, comédienne et chanteuse en pleine ascension, menacée par un fan inconnu.', 'https://www.youtube.com/embed/1NaoX3E1C38', 'https://fr.web.img6.acsta.net/medias/nmedia/18/66/03/99/19681697.jpg'),
(2, 2, 'La Calle de las Novias', 'Serie', 'publié', '2000-03-20', 'La Calle de las novias (Rue des fiancés - ou des mariés) est une rue typique de la ville de Mexico, très connue car on y trouve de nombreuses boutiques de mariages. Autour de cette rue se développe un quartier dynamique, une communauté solidaire. Une histoire d\'amour vient bousculer la tranquillité de la calle de las novias. Eduardo Sanchez est laissé pour mort, un soir dans la rue. Romàn Mendoza se déclare coupable de la mort du père de famille. Depuis cette tragédie, les familles Mendoza et Sanchez se livrent une guerre acharnée. Sept ans plus tard une histoire d\'amour vient à la fois envenimer et adoucir la guerre entre les deux familles. Deux enfants des familles Mendoza et Sanchez, à l\'instar de Roméo et Juliette, vont tomber amoureux, au désarroi des parents. Autour de ces deux personnages, Aura et Romàn, plusieurs couples se forment, se déchirent et inculquent aux téléspectateurs des valeurs de l\'amitié, de l\'amour et celles de la vie.', 'https://www.youtube.com/embed/K-tQW4gUJf0', 'https://pics.filmaffinity.com/La_calle_de_las_novias_Serie_de_TV-835131234-large.jpg'),
(3, 3, 'E.T., l\'extra-terrestre', 'Film', 'publié', '1982-12-01', 'Une soucoupe volante atterrit en pleine nuit près de Los Angeles. Quelques extraterrestres, envoyés sur Terre en mission d\'exploration botanique, sortent de l\'engin, mais un des leurs s\'aventure au-delà de la clairière où se trouve la navette. Celui-ci se dirige alors vers la ville. C\'est sa première découverte de la civilisation humaine. Bientôt traquée par des militaires et abandonnée par les siens, cette petite créature apeurée se nommant E.T. se réfugie dans une résidence de banlieue.', 'https://www.youtube.com/embed/zePPh2UAN_Y', 'https://fr.web.img4.acsta.net/medias/nmedia/00/02/36/52/affet.jpg'),
(4, 3, 'Charmed', 'Serie', 'publié', '1998-07-10', 'Charmed raconte l\'histoire de trois sœurs. Prue, Piper et Phoebe Halliwell qui découvrent qu\'elles sont les plus puissantes sorcières au Monde. Tout commence avec Phoebe qui trouve un grimoire magique nommé le Livre des Ombres et lit l\'incantation qui active leurs pouvoirs. Chacune en est dotée et elles doivent les utiliser pour défendre les innocents de San Francisco contre les démons, sorciers maléfiques et autres créatures du monde souterrain. Après trois années de luttes contre les forces démoniaques qui ont vu les pouvoirs des trois sœurs se transformer et évoluer, Prue (l\'aînée des trois sœurs) est tuée par Shax, l\'assassin de la Source du mal. La mort de Prue brise le « Pouvoir des Trois », qui sera finalement restauré par la découverte d\'une quatrième sœur Paige Matthews, qui se trouve être à moitié sorcière et à moitié Être de Lumière. Grâce au pouvoir des trois reformé, les trois sœurs peuvent se débarrasser, essentiellement par vengeance, de l\'assassin de Prue. Une fois leur destinée accomplie, Piper, Phoebe et Paige pensent être « tranquilles », mais elles doivent poursuivre leur mission car les démons cherchent toujours à voler leurs pouvoirs, tuer des innocents, s\'emparer du monde et réunifier les enfers pour devenir la prochaine Source du Mal…\r\n\r\n', 'https://www.youtube.com/embed/vDc7d88eAa4', 'https://image.tmdb.org/t/p/w500/z4bPJ1BWU2EtV69NII2GVvsugQ2.jpg'),
(5, 1, 'La Femme du Pasteur', 'Film', 'publié', '1996-12-13', 'Une voix off celle du petit Jérémiah (Justin Pierre Edmund) guide le spectateur tout au long du film.\r\n\r\nLe révérend Henry Biggs (Courtney B. Vance) est le pasteur d\'une petite église baptiste en déconfiture dans un quartier pauvre de New York. La fréquentation décline, Henry est tiraillé dans tous les sens par les besoins de ses paroissiens, et les finances de l\'église sont en mauvais état. Henry est soumis à une intense pression par le promoteur immobilier Joe Hamilton (Gregory Hines) pour qu’il vende les biens de l\'église afin que Hamilton puisse construire des condominiums de luxe sur son emplacement. Henry est également devenu négligent envers sa femme, Julia (Whitney Houston), et son fils, Jérémiah. Julia craint que son mariage soit en train de se défaire. Doutant qu\'il puisse changer la vie de ses paroissiens et commençant à perdre sa foi, Henry implore l’aide de Dieu, et elle vient sous les traits de Dudley (Denzel Washington), un ange spirituel et débonnaire. Dudley dit à Henry qu\'il est un ange envoyé par Dieu pour l\'aider, mais Henry est se méfie beaucoup de lui. Julia, au contraire, est instantanément séduite par la beauté et le calme de l\'ange.\r\n\r\nAvec l\'approche de Noël, le calendrier de Henry s’alourdit de plus en plus, et Dudley commence à passer la plus grande partie de son temps avec Julia et Jérémie. La secrétaire du pasteur, Beverly (Loretta Devine), devient agressive, croyant Dudley est là pour lui prendre son travail. La mère de Julia, Margueritte (Jenifer Lewis), qui a la langue acérée, se méfie elle aussi de Dudley, parce qu\'elle croit que le nouveau venu va briser le mariage de sa fille. Dudley et Julia vont faire du patin à glace, puis plus tard, passent une soirée dans le club de jazz où Julia se produisait autrefois. Après Henry s’est heurté à Dudley, celui-ci se rend compte qu\'il est en train de devenir amoureux de Julia. Il s’occupe alors de Hamilton, et parvient à brouiller les plans de Hamilton pour obtenir de Henry qu’il lui vende l\'église. Henry se rend compte alors que sa famille est la chose la plus importante dans sa vie, et il décide d\'être un meilleur mari et un meilleur père. Au spectacle de Noël de l\'église, Henry retrouve sa foi en Dieu et restaure ses liens avec sa famille.\r\n\r\nUne fois son travail fait, Dudley donne à la famille Biggs en cadeau un arbre de Noël entièrement décoré. Il efface ensuite tous les souvenirs de lui-même chez tous ceux qu\'il a rencontrés, et bien qu\'il assiste à un service de minuit pour la veillée de Noël, personne ne le reconnait. Jérémie, qui a une foi d’enfant, se souvient encore de Dudley, et lui souhaite un joyeux Noël.\r\n\r\nUne intrigue secondaire présente tout au long du film se concentre sur les talents de Julia dans le chant. Autrefois chanteuse dans un nightclub populaire, elle est maintenant une star dans le chœur de l\'église. Cette intrigue secondaire est le prétexte à plusieurs épisodes dans lesquels le chœur se produit et où la musique Gospel joue un rôle important. Elle apporte également un aspect comique en la personne d\'un chef de chorale dominateur.', 'https://www.youtube.com/embed/XbG_y-ZXdsU', 'https://img-4.linternaute.com/zLpgZB0jTs0L0CrHPkac8o0muos=/1240x/59c0abf9786b457fa766cf314970bfe0/ccmcms-linternaute/35053.jpg'),
(6, 1, 'Revenge', 'Serie', 'publié', '2011-09-21', 'Lorsqu\'elle était enfant, Amanda Clarke vivait dans les Hamptons jusqu\'à ce que son père soit arrêté. Soupçonné de terrorisme puis accusé et condamné, son père David Clarke meurt assassiné en prison. Amanda, qui n\'a pas de mère, est alors placée dans un foyer. Connaissant une adolescence difficile, Amanda fait elle-même un séjour en détention dans un centre juvénile. À sa sortie du milieu carcéral, Nolan, un ami de son père, vient la trouver et lui remet une boîte contenant des carnets appartenant à son père qui, juste avant de mourir, lui explique comment et par qui il a été trahi et piégé. Aujourd\'hui, la jeune femme revient dans son ancienne ville sous le nom d\'Emily Thorne et est prête à tout pour se venger de tous ceux qui sont à l\'origine du tragique destin de son père.', 'https://www.youtube.com/embed/DTQ3-FO_BoI', 'https://static1.purepeople.com/articles/0/15/89/80/@/1805558-affiche-promo-de-la-saison-4-de-revenge-624x600-2.jpg'),
(7, 3, 'Sixième Sens', 'Film', 'publié', '2000-01-05', 'Alors qu\'il rentre chez lui et s\'apprête à fêter avec son épouse une vie couronnée de succès, le Dr Malcolm Crowe, qui est psychologue pour enfants, a la surprise de voir l\'un de ses anciens patients, Vincent Grey, pénétrer chez lui. Cet homme est dénudé, sous l\'emprise d\'un stress important, et avec une arme à la main. L\'individu accuse Malcolm de l\'avoir abandonné à une vie de terreur puis lui tire dessus et se suicide.\r\n\r\nQuelque temps plus tard, le Dr Crowe fait la rencontre d\'un jeune garçon de 9 ans, Cole Sear, qui lui rappelle Vincent Grey au même âge. Comme lui, Cole est régulièrement en proie à des accès de terreur inexplicables, ce qui en fait une proie facile pour les autres garçons de son âge. Devant l\'impuissance de sa mère, le Dr Crowe décide de gagner peu à peu la confiance de l\'enfant, pour réussir là où il avait délaissé Vincent Grey précédement. Cet investissement personnel n\'est d\'ailleurs pas sans conséquence pour son couple, qui depuis l\'intrusion de Vincent Grey semble avoir des difficultés notamment au niveau communication.\r\n\r\nAprès plusieurs échanges, Cole révèle à Malcolm qu\'il voit des gens morts se déplacer autour de lui. Inconscients de leur état, parfois décédés dans des conditions terribles, ces morts croient continuer à vivre comme si de rien n’était, mais perçoivent la présence du jeune garçon qui interagit et communique avec eux, ce qui ne manque pas de le terroriser.\r\n\r\nRefusant tout d\'abord de croire à son histoire, le Dr Crowe en conclut qu\'il ne peut pas aider le jeune garçon, puisque son cas semble relever de la psychiatrie. Mais alors qu\'il réécoute un enregistrement sonore de Vincent Grey du temps où celui-ci fréquentait la même école que Cole, Malcolm a la surprise de constater, en poussant le son à fond, qu\'une voix suppliante se fait entendre, alors que Vincent était censé être seul dans la pièce. Le Dr Crowe doit alors se rendre à l\'évidence et admettre que les deux garçons n’ont pas menti.\r\n\r\nIl lui reste toutefois à comprendre pourquoi les morts se manifestent. Après avoir analysé plusieurs « visions » du jeune garçon, Malcolm émet l’hypothèse simple que les apparitions ont peut-être besoin d\'être écoutées, qu’elles ont en réalité besoin d\'aide pour comprendre leur situation. L\'initiative s\'avère bientôt payante puisque Cole prend peu à peu confiance en lui, et finit par accepter ce don : il aide ainsi l\'esprit d\'une fillette à révéler qu\'elle est morte empoisonnée par sa belle-mère, souffrant d\'un syndrome de Münchhausen par procuration.\r\n\r\nHeureux d\'avoir accompli sa mission, le Dr Crowe fait ses adieux à Cole, qui lui conseille de parler à sa femme alors qu\'elle est endormie. En effet, depuis l\'intrusion de Vincent Grey, son couple semble battre de l\'aile au point que sa femme est devenue froide et absente. Alors qu’il la trouve endormie sur le divan, visiblement sous l’effet de sédatifs, cette dernière laisse échapper un anneau d’or de ses mains. Constatant qu’il s’agit de son alliance alors qu’il était persuadé de l’avoir au doigt, Malcolm interroge à voix basse son épouse endormie, qui dans son sommeil l’entend, et lui parle pour la première fois depuis des mois.\r\n\r\nSon alliance lui a été retirée par sa femme, le soir où il est mort d’un coup de feu tiré par Vincent Grey. Depuis cette date tragique, Malcolm Crowe est un fantôme. Prenant enfin conscience de sa situation, il souffle un dernier mot d\'amour à sa femme avant de partir.', 'https://www.youtube.com/embed/YTtbg71Tj5M', 'https://fr.web.img6.acsta.net/medias/nmedia/18/66/15/77/19255607.jpg'),
(10, 3, 'Insaisissables', 'Film', 'publié', '2013-07-31', 'Les Quatre Cavaliers, un groupe de brillants magiciens et illusionnistes, vient de donner deux spectacles de magie époustouflants : le premier en braquant une banque sur un autre continent, le deuxième en transférant la fortune d\'un banquier véreux sur les comptes en banque du public. Deux agents spéciaux du FBI et d\'Interpol sont déterminés à les arrêter avant qu\'ils ne mettent à exécution leur promesse de réaliser des braquages encore plus audacieux.', 'https://www.youtube.com/embed/-FeFz-cXILA', 'https://fr.web.img4.acsta.net/pictures/210/082/21008214_20130524171741313.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(254) NOT NULL,
  `password` varchar(80) NOT NULL,
  `active` enum('0','1') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `password`, `active`) VALUES
(1, 'coding@gmail.com', '123456', '1'),
(3, 'antoinedubranchet@gmail.com', 'azerty', '0'),
(4, 'titi@lala.fr', 'lalala', '1'),
(7, 'tania.rojas@edu.itescia.fr', 'wxcvbn', '1'),
(8, 'trang_430@hotmail.com', 'qwerty', '1'),
(9, 'azerty@gmail.com', 'c31ebcdb8bf3ca2701279471b259b16c6fceff47c4156ba94f2246fa30c7770e', '1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `genre`
--
ALTER TABLE `genre`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `history`
--
ALTER TABLE `history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `history_user_id_fk_media_id` (`user_id`),
  ADD KEY `history_media_id_fk_media_id` (`media_id`);

--
-- Indexes for table `media`
--
ALTER TABLE `media`
  ADD PRIMARY KEY (`id`),
  ADD KEY `media_genre_id_fk_genre_id` (`genre_id`) USING BTREE;

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `genre`
--
ALTER TABLE `genre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `history`
--
ALTER TABLE `history`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `media`
--
ALTER TABLE `media`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `history`
--
ALTER TABLE `history`
  ADD CONSTRAINT `history_media_id_fk_media_id` FOREIGN KEY (`media_id`) REFERENCES `media` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `history_user_id_fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `media`
--
ALTER TABLE `media`
  ADD CONSTRAINT `media_genre_id_b1257088_fk_genre_id` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
